package cn.edu.seu.kse.anubis.lpmln.split;

import cn.edu.seu.kse.anubis.lpmln.splitting.BotWithTop;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 许鸿翔 on 2017/9/10.
 */
public class split {
    @Test
    public void testBotWithTop(){
        File file = new File("D:\\ideaSpace\\lpmlnmodels\\test.lp");
        List<File> topFile = new ArrayList<>();
        topFile.add(file);
        topFile.add(file);
        topFile.add(file);
        topFile.add(file);
        Set<String> ans = BotWithTop.getRealAnswerset(file,topFile);
        return;
    }

}
