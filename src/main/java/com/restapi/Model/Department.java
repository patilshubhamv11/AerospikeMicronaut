package com.restapi.Model;

import com.aerospike.mapper.annotations.AerospikeRecord;

@AerospikeRecord
public class Department {
    private int deptid;
    private String deptname;

    public Department() {
    }

    public Department(int deptid, String deptname) {
        this.deptid = deptid;
        this.deptname = deptname;
    }



    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptid=" + deptid +
                ", deptname='" + deptname + '\'' +
                '}';
    }
}
