// This is a generated file. Not intended for manual editing.
package com.xxxlin.jsgf.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsgfItem extends PsiElement {

  @Nullable
  JsgfDocument getDocument();

  @Nullable
  JsgfExp getExp();

  @Nullable
  JsgfGrammar getGrammar();

  @Nullable
  PsiElement getBlockComment();

  @Nullable
  PsiElement getLineComment();

}
