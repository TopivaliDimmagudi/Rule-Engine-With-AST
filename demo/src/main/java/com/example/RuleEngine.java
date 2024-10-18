package com.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RuleEngine 
{
    private Set<String> validAttributes;

    public RuleEngine(Set<String> attributes)
     {
        this.validAttributes = attributes;
    }

    /**
     * @param ruleString
     * @return
     */
    public Node create_rule(String ruleString) 
    {
        Stack<Node> stack = new Stack<>();
        String[] tokens = ruleString.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ").split("\\s+");

        for (String token : tokens) {
            if (token.equals("AND") || token.equals("OR")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid rule: Missing operands for operator " + token);
                }
                Node operatorNode = new Node("operator", token);
                operatorNode.right = stack.pop();
                operatorNode.left = stack.pop();
                stack.push(operatorNode);
            } else if (token.equals("(")) {
            
            } else if (token.equals(")")) {
                
            
            } else {
                
                validateOperand(token);
                stack.push(new Node("operand", token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid rule format");
        }

        return stack.pop(); 
    }

    private void validateOperand(String operand) {
        String[] parts = operand.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid operand format: " + operand);
        }
        String attribute = parts[0];
        if (!validAttributes.contains(attribute)) {
            throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }

        
    }

    public Node combine_rules(List<String> ruleStrings) {
        Node combinedAST = null;

        for (String rule : ruleStrings) {
            Node currentAST = create_rule(rule);
            if (combinedAST == null) {
                combinedAST = currentAST;
            } else {
                Node newRoot = new Node("operator", "AND");
                newRoot.left = combinedAST;
                newRoot.right = currentAST;
                combinedAST = newRoot;
            }
        }
        return combinedAST;
    }

    public boolean evaluate_rule(Node node, JSONObject data) throws NumberFormatException {
        if (node.type.equals("operand")) {
            String[] parts = node.value.split(" ");
            String attribute = parts[0];
            String operator = parts[1];
            String value = parts[2];

            switch (operator) {
                case ">":
                    try {
                        return data.getInt(attribute) > Integer.parseInt(value);
                    } catch (NumberFormatException | JSONException e) {
                        
                        e.printStackTrace();
                    }
                case "<":
                    try {
                        return data.getInt(attribute) < Integer.parseInt(value);
                    } catch (NumberFormatException | JSONException e) {
                    
                        e.printStackTrace();
                    }
                case "=":
                    try {
                        return data.getString(attribute).equals(value);
                    } catch (JSONException e) {
                        
                        e.printStackTrace();
                    }
            
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
        } else if (node.type.equals("operator")) {
            boolean leftValue = evaluate_rule(node.left, data);
            boolean rightValue = evaluate_rule(node.right, data);

            if (node.value.equals("AND")) {
                return leftValue && rightValue;
            } else if (node.value.equals("OR")) {
                return leftValue || rightValue;
            }
        }
        return false; 
    }

    public void modify_rule(Node node, String newValue) {
        if (node.type.equals("operand")) {
            node.value = newValue;
        } else {
            throw new UnsupportedOperationException("Cannot modify operator nodes directly.");
        }
    }

    public Set<String> getValidAttributes() {
        return validAttributes;
    }

    public void setValidAttributes(Set<String> validAttributes) {
        this.validAttributes = validAttributes;
    }
}

