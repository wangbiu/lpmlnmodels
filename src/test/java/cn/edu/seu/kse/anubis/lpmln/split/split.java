package cn.edu.seu.kse.anubis.lpmln.split;

import cn.edu.seu.kse.anubis.lpmln.splitting.BotWithTop;
import org.junit.Test;

/**
 * Created by 许鸿翔 on 2017/9/10.
 */
public class split {
    @Test
    public void testBotWithTop(){
//        File file = new File("D:\\ideaSpace\\lpmlnmodels\\test.lp");
//        List<File> topFile = new ArrayList<>();
//        topFile.add(file);
//        topFile.add(file);
//        topFile.add(file);
//        topFile.add(file);
//        Set<String> ans = BotWithTop.executeExperiment(file,topFile);
        BotWithTop.executeExperiment(10,14);
        return;
    }

}
