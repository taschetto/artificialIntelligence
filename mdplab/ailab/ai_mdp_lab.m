addpath("../MDPtoolbox");

args.gsize = [3,4];
args.Pm = .8;
args.obstacles = {[2,2]};
args.terminals = {[1,4],[2,4]};
args.rewards = {[1,4,100],[2,4,-100]};
args.R = -3;
discount = 0.1;
epsilon = 0.01; % This is the threshold variation of utility per iteration that defines convergence
max_iter = 200; % This is the maximum number of iterations for the Bellman equation

[P,R] = gen_scenario('grid',args);

% disp(P); % Displays the transition function 
% Note that for P, the last dimension of the table is the action, where:
% 1 = North, 2 = South, 3 = East, 4 = West
% disp(R); % Displays the reward function, where 1 = a1, 2 = b1, 3 = c1, 4 = a2, and so on

V0 = zeros(size(P,1),1);

% Code to start computing the MDP values

% A = mdp_value_iteration(P,R,1,0.01,200,zeros(size(P,1),1));
 A = mdp_value_iteration(P,R,discount,epsilon,max_iter,V0);

ActNames = ['N','S','E','W'];
disp_policy(ActNames,A,args.gsize);
