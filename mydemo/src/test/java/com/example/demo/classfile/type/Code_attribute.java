package com.example.demo.classfile.type;

/**
 * @Author XZQ
 * @Date 2021/10/2 00:03
 **/
public class Code_attribute {
    private U2 attritube_name_index;
    private U4 attribute_length;
    private U2 max_stack;
    private U2 max_locals;
    private U4 code_length;
    private byte[] code;
    private U4 exception_table_length;
    private Exception[] exception_table;
    private U2 attribute_count;
    private Attributeinfo[] attributeinfos;
    //异常表中每项的结构
    public static class Exception{
        private U2 start_pc;
        private U2 end_pc;
        private U2 handler_pc;
        private U2 catch_type;
    }

    public U2 getAttritube_name_index() {
        return attritube_name_index;
    }

    public void setAttritube_name_index(U2 attritube_name_index) {
        this.attritube_name_index = attritube_name_index;
    }

    public U4 getAttribute_length() {
        return attribute_length;
    }

    public void setAttribute_length(U4 attribute_length) {
        this.attribute_length = attribute_length;
    }

    public U2 getMax_stack() {
        return max_stack;
    }

    public void setMax_stack(U2 max_stack) {
        this.max_stack = max_stack;
    }

    public U2 getMax_locals() {
        return max_locals;
    }

    public void setMax_locals(U2 max_locals) {
        this.max_locals = max_locals;
    }

    public U4 getCode_length() {
        return code_length;
    }

    public void setCode_length(U4 code_length) {
        this.code_length = code_length;
    }

    public U4 getException_table_length() {
        return exception_table_length;
    }

    public void setException_table_length(U4 exception_table_length) {
        this.exception_table_length = exception_table_length;
    }

    public Exception[] getException_table() {
        return exception_table;
    }

    public void setException_table(Exception[] exception_table) {
        this.exception_table = exception_table;
    }

    public U2 getAttribute_count() {
        return attribute_count;
    }

    public void setAttribute_count(U2 attribute_count) {
        this.attribute_count = attribute_count;
    }

    public Attributeinfo[] getAttributeinfos() {
        return attributeinfos;
    }

    public void setAttributeinfos(Attributeinfo[] attributeinfos) {
        this.attributeinfos = attributeinfos;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }
}
