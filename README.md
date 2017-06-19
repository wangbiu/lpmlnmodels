# Status
LPMLN: a language combining non-monotonic logic and Markov Networks.
This repository is an implementation of LPMLN solver.
The implementation is currently under development and is not ready to use. 

# LPMLN Experiments 
+ Bird 
+ Friend 

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

