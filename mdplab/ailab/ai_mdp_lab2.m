addpath ( "../MDPtoolbox" ) ;
args.gsize = [3 ,4];
args.Pm= .8;
args.obstacles = {[2 ,2]};
args.terminals = {[1 ,4] ,[2 ,4]};
args.rewards = {[1 ,4 ,100] ,[2 ,4 , -100]}; 
args.R = -3;
discount = 0.9;
[P,R] = gen_scenario('grid',args);
PR = mdp_computePR(P,R); V0 = zeros(size(P,1) ,1);
V = V0;
% The line below is the one that will be repeated
[V, policy ] = mdp_bellman_operator(P, PR, discount, V); disp(V);
disp('--------------------------------------------------------')
[V, policy ] = mdp_bellman_operator(P, PR, discount, V); disp(V);
disp('--------------------------------------------------------')
[V, policy ] = mdp_bellman_operator(P, PR, discount, V); disp(V);
disp('--------------------------------------------------------')
