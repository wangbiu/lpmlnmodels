# lpmlnmodels 推理机参数
```lpmlnmodels <params> <lpmln file>```
 
+ `--marginal` 可选，计算边缘概率分布，该参数不指定时，计算最大可能稳定模型
+ `--showall` 可选，显示全部稳定模型
+ `--asp clingo | dlv` 必选，指定使用的ASP推理机
+ `--transtype strong | weak` 可选，指定翻译的类型，默认是强翻译
+ `--transout <filename>` 可选，将翻译结果输出到指定文件中
+ 实验相关（目前仅支持Clingo推理机）
+ `--experiment` 可选，进入实验模式，忽略推理相关参数，仅执行实验相关任务
+ `--exp-problem-n` 必填，Monty Hall Problem 的初始规模，门（盒子）的个数
+ `--exp-max-problem-n` 必填，Monty Hall Problem 的最大规模，门（盒子）的个数，目前最大支持40
+ `--exp-round` 必填，每组实验执行的次数，取平均值作为一组实验的数据，避免系统误差
+ `--exp-task-id` 必填，执行的任务类型，0 = 最大可能世界， 1 = 边缘概率分布， 2 = 全部
+ `--parallel` 可选，是否使用并行模式
+ `--exp=cores` 必填，并行模式下的最小线程数
+ `--exp-max-cores` 必填，并行模式下的最大线程数

# LPMLN Experiments 
+ Bird 
+ Friend 
+ Monty Hall Problem (3-40)

Files with suffix `origin` are original LPMLN programs, 
files with suffix `clingo` are ASP programs in Clingo syntax,
files with suffix `dlv` are ASP programs in Clingo syntax.

# Version
+ Clingo 4.3.0
+ DLV  build Dec 17 2012

# Command line
+ clingo 0 --opt-mode enum \<file\>
+ dlv -costbound=_,_ \<file\>

# Note
Syntax about weak constraint is slightly different between Clingo and DLV, 
but the clingo syntax is standard ASP syntax.

