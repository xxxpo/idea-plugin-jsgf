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

public class JsgfVariableValueImpl extends ASTWrapperPsiElement implements JsgfVariableValue {

  public JsgfVariableValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsgfVisitor visitor) {
    visitor.visitVariableValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsgfVisitor) accept((JsgfVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JsgfSlopString getSlopString() {
    return findNotNullChildByClass(JsgfSlopString.class);
  }

}
