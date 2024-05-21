// This is a generated file. Not intended for manual editing.
package com.xxxlin.jsgf.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.xxxlin.jsgf.JsgfElementType;
import com.xxxlin.jsgf.JsgfTokenType;
import com.xxxlin.jsgf.psi.impl.*;

public interface JsgfTypes {

  IElementType DOCUMENT = new JsgfElementType("DOCUMENT");
  IElementType EXP = new JsgfElementType("EXP");
  IElementType GRAMMAR = new JsgfElementType("GRAMMAR");
  IElementType ITEM = new JsgfElementType("ITEM");
  IElementType SLOP_STRING = new JsgfElementType("SLOP_STRING");
  IElementType VARIABLE_VALUE = new JsgfElementType("VARIABLE_VALUE");

  IElementType BLOCK_COMMENT = new JsgfTokenType("BLOCK_COMMENT");
  IElementType DOC_ENCODE = new JsgfTokenType("DOC_ENCODE");
  IElementType DOC_VERSION = new JsgfTokenType("DOC_VERSION");
  IElementType GRAMMAR_NAME = new JsgfTokenType("GRAMMAR_NAME");
  IElementType ID = new JsgfTokenType("id");
  IElementType JSGF_LANGUAGE = new JsgfTokenType("JSGF_LANGUAGE");
  IElementType KEYWORD = new JsgfTokenType("KEYWORD");
  IElementType LABEL_STRING = new JsgfTokenType("LABEL_STRING");
  IElementType LEFT_BRACE = new JsgfTokenType("{");
  IElementType LEFT_BRACKET = new JsgfTokenType("[");
  IElementType LEFT_PAREN = new JsgfTokenType("(");
  IElementType LINE_COMMENT = new JsgfTokenType("LINE_COMMENT");
  IElementType OP_AND = new JsgfTokenType("&");
  IElementType OP_EQ = new JsgfTokenType("=");
  IElementType OP_IS = new JsgfTokenType("::=");
  IElementType OP_NOT = new JsgfTokenType("!");
  IElementType OP_ONEMORE = new JsgfTokenType("+");
  IElementType OP_OPT = new JsgfTokenType("?");
  IElementType OP_OR = new JsgfTokenType("|");
  IElementType OP_ZEROMORE = new JsgfTokenType("*");
  IElementType RIGHT_BRACE = new JsgfTokenType("}");
  IElementType RIGHT_BRACKET = new JsgfTokenType("]");
  IElementType RIGHT_PAREN = new JsgfTokenType(")");
  IElementType SEMICOLON = new JsgfTokenType(";");
  IElementType SLOP_STRING_LITERAL = new JsgfTokenType("SLOP_STRING_LITERAL");
  IElementType STRING = new JsgfTokenType("STRING");
  IElementType TEXT = new JsgfTokenType("");
  IElementType VARIABLE_NAME = new JsgfTokenType("VARIABLE_NAME");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == DOCUMENT) {
        return new JsgfDocumentImpl(node);
      }
      else if (type == EXP) {
        return new JsgfExpImpl(node);
      }
      else if (type == GRAMMAR) {
        return new JsgfGrammarImpl(node);
      }
      else if (type == ITEM) {
        return new JsgfItemImpl(node);
      }
      else if (type == SLOP_STRING) {
        return new JsgfSlopStringImpl(node);
      }
      else if (type == VARIABLE_VALUE) {
        return new JsgfVariableValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
