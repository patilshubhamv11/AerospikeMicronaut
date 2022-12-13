package com.restapi.casbinutil;

import com.restapi.Model.Policy;
import jakarta.inject.Singleton;
import org.casbin.jcasbin.exception.CasbinAdapterException;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.FilteredAdapter;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CasbinAeroAdapter  extends AerospikebaseAdapter implements FilteredAdapter {

    @Override
    public void addPolicy(String sec, String ptype, List<String> rule) {

        int id = (int) (Math.random() * 100000);

        Iterator<String> e = rule.iterator();
        Policy pojo = new Policy();
        pojo.setId(id);
        pojo.setPtype(ptype);
        pojo.setV0(e.hasNext() ? e.next().toString() : "");
        pojo.setV1(e.hasNext() ? e.next().toString() : "");
        pojo.setV2(e.hasNext() ? e.next().toString() : "");
        pojo.setV3(e.hasNext() ? e.next().toString() : "");
        pojo.setV4(e.hasNext() ? e.next().toString() : "");
        pojo.setV5(e.hasNext() ? e.next().toString() : "");
        aeroMapperUtil.getMapper().save(pojo);
    }

    @Override
    public void removePolicy(String sec, String ptype, List<String> rule) {

        List<Policy> policyModels = aeroMapperUtil.getMapper().scan(Policy.class);
        if (policyModels != null) {
            policyModels = policyModels.stream().filter(x -> x.getPtype().equals(ptype))
                    .filter(u -> u.getV0().equals(rule.get(0))).filter(r -> r.getV1().equals(rule.get(1)))
                    .collect(Collectors.toList());
        }
        if (policyModels != null) {
            long id = policyModels.get(0).getId();
            aeroMapperUtil.getMapper().delete(Policy.class, id);
        }
    }

    @Override
    public void removeFilteredPolicy(String sec, String ptype, int fieldIndex, String... fieldValues) {

        List<Policy> pojo = aeroMapperUtil.getMapper().scan(Policy.class);
        if (pojo != null) {
            pojo = pojo.stream().filter(x -> x.getPtype().equals(ptype))
                    .filter(u -> u.getV0().equals(fieldValues[0])).collect(Collectors.toList());
        }
        assert pojo != null;
        for (Policy pojo1 : pojo) {
            aeroMapperUtil.getMapper().delete(Policy.class, pojo1.getId());
        }
    }

    @Override
    public void savePolicy(Model model) {
    }


    @Override
    public void loadFilteredPolicy(Model model, Object o) throws CasbinAdapterException {

    }

    @Override
    public boolean isFiltered() {
        return false;
    }

    @Override
    public void addPolicies(String s, String s1, List<List<String>> list) {

    }

    @Override
    public void removePolicies(String s, String s1, List<List<String>> list) {

    }
}