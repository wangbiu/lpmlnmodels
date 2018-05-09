package cn.edu.seu.kse.lpmln.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 许鸿翔
 * @date 2018/5/9
 */
public class UnionFindSet<T> {
    private int[] pre;
    Map<T,Integer> idxMap;
    Map<Integer,T> itemMap;

    public UnionFindSet(Collection<T> sth){
        pre = new int[sth.size()];
        idxMap = new HashMap<>();
        itemMap = new HashMap<>();
        sth.forEach(item->{
            idxMap.put(item,idxMap.size());
            itemMap.put(itemMap.size(),item);
            pre[idxMap.size()] = idxMap.size();
        });
    }

    public T find(T x){
        return itemMap.get(find(idxMap.get(x)));
    }

    public void join(T x,T y){
        join(idxMap.get(x),idxMap.get(y));
    }

    private int find(int x)
    {
        int r=x;
        while(pre[r]!=r){
            //找到他的前导结点
            r=pre[r];
        }
        int i=x,j;
        //路径压缩算法
        while(i!=r)
        {
            //记录x的前导结点
            j=pre[i];
            //将i的前导结点设置为r根节点
            pre[i]=r;
            i=j;
        }
        return r;
    }

    private void join(int x,int y)
    {
        //x的根节点为a
        int a=find(x);
        //y的根节点为b
        int b=find(y);
        //如果a,b不是相同的根节点，则说明ab不是连通的
        if(a!=b)
        {
            //我们将ab相连 将a的前导结点设置为b
            pre[a]=b;
        }
    }
}
