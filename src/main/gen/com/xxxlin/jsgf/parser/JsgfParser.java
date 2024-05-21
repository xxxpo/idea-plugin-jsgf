// This is a generated file. Not intended for manual editing.
package com.xxxlin.jsgf.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.xxxlin.jsgf.psi.JsgfTypes.*;
import static com.xxxlin.jsgf.parser.JsgfParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JsgfParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return jsgfFile(b, l + 1);
  }

  /* ********************************************************** */
  // slop_string
  public static boolean VARIABLE_VALUE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VARIABLE_VALUE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_VALUE, "<variable value>");
    r = slop_string(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD DOC_VERSION DOC_ENCODE JSGF_LANGUAGE ";"
  public static boolean document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document")) return false;
    if (!nextTokenIs(b, KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD, DOC_VERSION, DOC_ENCODE, JSGF_LANGUAGE, SEMICOLON);
    exit_section_(b, m, DOCUMENT, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD? VARIABLE_NAME "=" VARIABLE_VALUE ";"
  public static boolean exp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exp")) return false;
    if (!nextTokenIs(b, "<exp>", KEYWORD, VARIABLE_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXP, "<exp>");
    r = exp_0(b, l + 1);
    r = r && consumeTokens(b, 0, VARIABLE_NAME, OP_EQ);
    r = r && VARIABLE_VALUE(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD?
  private static boolean exp_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exp_0")) return false;
    consumeToken(b, KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD GRAMMAR_NAME ";"
  public static boolean grammar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "grammar")) return false;
    if (!nextTokenIs(b, KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD, GRAMMAR_NAME, SEMICOLON);
    exit_section_(b, m, GRAMMAR, r);
    return r;
  }

  /* ********************************************************** */
  // document|grammar|LINE_COMMENT|BLOCK_COMMENT|exp
  public static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ITEM, "<item>");
    r = document(b, l + 1);
    if (!r) r = grammar(b, l + 1);
    if (!r) r = consumeToken(b, LINE_COMMENT);
    if (!r) r = consumeToken(b, BLOCK_COMMENT);
    if (!r) r = exp(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item*
  static boolean jsgfFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsgfFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jsgfFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (SLOP_STRING_LITERAL | LINE_COMMENT | BLOCK_COMMENT | LABEL_STRING | VARIABLE_NAME | "(" slop_string ")" | "[" slop_string "]" ) slop_string* (OP_OR slop_string)*
  public static boolean slop_string(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SLOP_STRING, "<slop string>");
    r = slop_string_0(b, l + 1);
    r = r && slop_string_1(b, l + 1);
    r = r && slop_string_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SLOP_STRING_LITERAL | LINE_COMMENT | BLOCK_COMMENT | LABEL_STRING | VARIABLE_NAME | "(" slop_string ")" | "[" slop_string "]"
  private static boolean slop_string_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLOP_STRING_LITERAL);
    if (!r) r = consumeToken(b, LINE_COMMENT);
    if (!r) r = consumeToken(b, BLOCK_COMMENT);
    if (!r) r = consumeToken(b, LABEL_STRING);
    if (!r) r = consumeToken(b, VARIABLE_NAME);
    if (!r) r = slop_string_0_5(b, l + 1);
    if (!r) r = slop_string_0_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "(" slop_string ")"
  private static boolean slop_string_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string_0_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PAREN);
    r = r && slop_string(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" slop_string "]"
  private static boolean slop_string_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string_0_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && slop_string(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // slop_string*
  private static boolean slop_string_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!slop_string(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "slop_string_1", c)) break;
    }
    return true;
  }

  // (OP_OR slop_string)*
  private static boolean slop_string_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!slop_string_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "slop_string_2", c)) break;
    }
    return true;
  }

  // OP_OR slop_string
  private static boolean slop_string_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slop_string_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_OR);
    r = r && slop_string(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
