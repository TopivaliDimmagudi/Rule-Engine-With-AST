package com.example;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

public class RuleEngineTest 
{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        
        return super.clone();
    }

    @Override
    public boolean equals(Object arg0) {
        
        return super.equals(arg0);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        
        super.finalize();
    }

    @Override
    public int hashCode() {
    
        return super.hashCode();
    }

    /**
     * @param args
     * @throws JSONException
     */
    public static void main(String[] args) throws JSONException {
        
        HashSet<String> validAttributes = new HashSet<>(Arrays.asList("age", "department", "salary", "experience"));

        RuleEngine engine = new RuleEngine(validAttributes);

        
        try {
            System.out.println("AST1 created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating AST1: " + e.getMessage());
        }

        
        try {
            System.out.println("Combined AST created successfully.");
        } catch (Exception e) {
            System.out.println("Error combining rules: " + e.getMessage());
        }

        
        JSONObject data = new JSONObject();
        data.put("age", 35);
        try {
            data.put("department", "Sales");
        } catch (JSONException e) 
        {
        
            e.printStackTrace();
        }

        try 
        {
            boolean result = engine.evaluate_rule(
                engine.create_rule("age > 30 AND department = 'Sales'"),
                data
            );
            System.out.println("Evaluation result: " + result); 
        } catch (Exception e)
         {
            System.out.println("Error evaluating rule: " + e.getMessage());
        }

    
        Node operandNode = new Node("operand", "age > 30");
        try {
            engine.modify_rule(operandNode, "age < 40");
            System.out.println("Modified operand: " + operandNode.value); 
        } catch (Exception e) {
            System.out.println("Error modifying rule: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "RuleEngineTest []";
    }
}

