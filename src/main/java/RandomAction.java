import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;

public class RandomAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull final AnActionEvent e) {
        if (e.getProject() == null) {
            return;
        }

        List<VirtualFile> files = new LinkedList<>();

        ProjectFileIndex.getInstance(e.getProject()).iterateContent(files::add,
                file -> file.getExtension() != null && file.getExtension().equalsIgnoreCase("java"));
        int i = rnd.nextInt(files.size());
        VirtualFile file = files.get(i);

        new OpenFileDescriptor(e.getProject(), file).navigate(true);
    }

    private Random rnd = new Random();
}
