 # Project Name : Rule Engine with AST
Overview :
This project implements a simple rule engine using an Abstract Syntax Tree (AST) to evaluate user eligibility based on various attributes such as age, department, income, and experience. The rule engine allows dynamic creation, combination, and modification of rules, featuring error handling and attribute validation.

Features :

AST Representation: Rules are parsed and represented as a tree structure for efficient evaluation.
Dynamic Rule Creation: Create rules from strings and convert them into AST nodes.
Rule Combination: Combine multiple rules into a single AST.
Evaluation: Evaluate rules against user attributes provided in JSON format.
Error Handling: Handles invalid rule strings and unsupported operators gracefully.
Attribute Validation: Validates that attributes are part of a predefined catalog.
Rule Modification: Allows modification of existing operand values.
Getting Started
Prerequisites :
Java Development Kit (JDK) 8 or higher
JSON library (e.g., org.json)
