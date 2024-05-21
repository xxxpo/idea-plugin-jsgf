// This is a generated file. Not intended for manual editing.
package com.xxxlin.jsgf.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.xxxlin.jsgf.psi.JsgfTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.xxxlin.jsgf.psi.*;
import com.xxxlin.jsgf.JsgfPsiImplUtil;

public class JsgfItemImpl extends ASTWrapperPsiElement implements JsgfItem {

  public JsgfItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsgfVisitor visitor) {
    visitor.visitItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsgfVisitor) accept((JsgfVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsgfDocument getDocument() {
    return findChildByClass(JsgfDocument.class);
  }

  @Override
  @Nullable
  public JsgfExp getExp() {
    return findChildByClass(JsgfExp.class);
  }

  @Override
  @Nullable
  public JsgfGrammar getGrammar() {
    return findChildByClass(JsgfGrammar.class);
  }

  @Override
  @Nullable
  public PsiElement getBlockComment() {
    return findChildByType(BLOCK_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getLineComment() {
    return findChildByType(LINE_COMMENT);
  }

}
