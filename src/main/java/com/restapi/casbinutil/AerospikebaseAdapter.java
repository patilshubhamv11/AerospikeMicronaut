package com.restapi.casbinutil;

import com.restapi.Configuration.aeroMapperConfig;
import com.restapi.Model.Policy;
import jakarta.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.Adapter;
import org.casbin.jcasbin.persist.BatchAdapter;
import org.casbin.jcasbin.persist.Helper;
import org.casbin.jcasbin.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


class CasbinRule {
    int id;
    String ptype;
    String v0;
    String v1;
    String v2;
    String v3;
    String v4;
    String v5;

    public String[] toStringArray() {
        return new String[]{ptype, v0, v1, v2, v3, v4, v5};
    }
}

abstract class AerospikebaseAdapter implements Adapter, BatchAdapter {

    @Inject
    aeroMapperConfig aeroMapperUtil;

    @Override
    public void loadPolicy(Model model) {

        List<Policy> policyModels = aeroMapperUtil.getMapper().scan(Policy.class);
        //FOR FIRST TIME USE
        if (policyModels == null || policyModels.size() == 0) {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("casbin/policy.csv");
            if(inputStream != null) {
                List<String> lines = null;
                Policy[] policyModelsArray = null;
                try {
                    lines = IOUtils.readLines(inputStream, Charset.forName("UTF-8"));
                    Function<String, Policy> function =getPolicyModelCreateFunction();
                    policyModels = new ArrayList<>();
                    List<Policy> finalPolicyModels = policyModels;
                    lines.forEach((x) -> {
                        Helper.loadPolicyLine(x, model);
                        finalPolicyModels.add(function.apply(x));
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                policyModelsArray = policyModels.stream().filter(p -> p!=null).toArray(Policy[]::new);
                aeroMapperUtil.getMapper().save(policyModelsArray);
            }
        } else {
            for (Policy policyModel : policyModels) {
                CasbinRule line = new CasbinRule();
                line.ptype = policyModel.getPtype() == null ? "" : (String) policyModel.getPtype();
                line.v0 = policyModel.getV0();
                line.v1 = policyModel.getV1();
                line.v2 = policyModel.getV2();
                line.v3 = policyModel.getV3();
                line.v4 = policyModel.getV4();
                line.v5 = policyModel.getV5();
                loadPolicyLine(line, model);
            }
        }
    }

    private Function<String, Policy> getPolicyModelCreateFunction() {
        Function<String, Policy> function = (p) -> {
            Policy policyModel = null;
            if (!"".equals(p)) {
                if (p.charAt(0) != '#') {
                    policyModel = new Policy();
                    String[] tokens = Util.splitCommaDelimited(p);
                    policyModel.setId(RandomUtils.nextLong(1, 100000));
                    if (tokens.length > 0) {
                        policyModel.setPtype(tokens[0]);
                    }
                    if (tokens.length > 1) {
                        policyModel.setV0(tokens[1]);
                    }
                    if (tokens.length > 2) {
                        policyModel.setV1(tokens[2]);
                    }
                    if (tokens.length > 3) {
                        policyModel.setV2(tokens[3]);
                    }
                    if (tokens.length > 4) {
                        policyModel.setV3(tokens[4]);
                    }
                    if (tokens.length > 5) {
                        policyModel.setV4(tokens[5]);
                    }
                    if (tokens.length > 6) {
                        policyModel.setV5(tokens[6]);
                    }
                }
            }
            return  policyModel;
        };
        return function;
    }

    protected void loadPolicyLine(CasbinRule line, Model model) {
        String lineText = line.ptype;
        if (!"".equals(line.v0)) {
            lineText += ", " + line.v0;
        }
        if (!"".equals(line.v1)) {
            lineText += ", " + line.v1;
        }
        if (!"".equals(line.v2)) {
            lineText += ", " + line.v2;
        }
        if (!"".equals(line.v3)) {
            lineText += ", " + line.v3;
        }
        if (!"".equals(line.v4)) {
            lineText += ", " + line.v4;
        }
        if (!"".equals(line.v5)) {
            lineText += ", " + line.v5;
        }

        Helper.loadPolicyLine(lineText, model);
    }
}