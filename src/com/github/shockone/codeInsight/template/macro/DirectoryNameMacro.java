package com.github.shockone.codeInsight.template.macro;

import com.intellij.codeInsight.template.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Volodymyr Shatskyi
 */
public class DirectoryNameMacro extends Macro {
    @Override
    public String getName() {
        return "directoryName";
    }

    @Override
    public String getPresentableName() {
        return "directoryName()";
    }

    @Override
    public Result calculateResult(@NotNull Expression[] params, ExpressionContext context) {
        final PsiFile file = PsiDocumentManager.getInstance(context.getProject()).getPsiFile(context.getEditor().getDocument());
        if (file != null) {
            final VirtualFile virtualFile = file.getVirtualFile();
            if (virtualFile != null) {
                return calculateResult(virtualFile);
            }
        }
        return null;
    }

    @Nullable
    private TextResult calculateResult(@NotNull VirtualFile virtualFile) {
        return new TextResult(virtualFile.getParent().getName());
    }
}

