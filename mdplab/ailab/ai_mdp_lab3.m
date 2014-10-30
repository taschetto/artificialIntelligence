addpath("../MDPtoolbox");

args.gsize = [5,5];
args.Pm = .8;
args.obstacles = {[1,1],[1,3],[3,1],[3,3]};
args.terminals = {[2,1],[2,2],[1,2],[3,2]};
args.rewards = {[2,1,-100],[2,2,100],[1,2,-100],[3,2,-100]};
args.R = -3;
discount = 0.99;
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
