package com.restapi.casbinutil;

import com.restapi.Application;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.file_adapter.FileAdapter;

import java.nio.charset.Charset;

public class CasbinEnforcerutil {
    private static volatile Enforcer ENFORCER;
    private CasbinEnforcerutil() {
    }
    private static Logger LOGGER = LogManager.getLogger(CasbinEnforcerutil.class.getName());
    public static Enforcer getCasbinEnForcer() {
        if (ENFORCER == null) {
            synchronized (CasbinEnforcerutil.class) {
                if (ENFORCER == null) {
                    try {
                        Model model = new Model();
                        model.loadModelFromText(IOUtils.toString(ClassLoader.getSystemResource("casbin/model.conf"), Charset.defaultCharset()));
                        //FileAdapter fileAdapter = new FileAdapter(ClassLoader.getSystemResourceAsStream("casbin/policy.csv"));
                        CasbinAeroAdapter aeroAdapter = Application.getApplicationContext().getBean(CasbinAeroAdapter.class);
                        ENFORCER = new Enforcer(model, aeroAdapter);
                    } catch (Exception e) {
                        if (LOGGER.isErrorEnabled())
                            LOGGER.error("Enforcer creation failed : " + e.getMessage());
                    }
                }
            }
        }
        return ENFORCER;
    }
}