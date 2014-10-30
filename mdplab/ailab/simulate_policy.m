function [normativeReward,individualReward] = simulate_policy(iS, policy, P, R, Rn, T)
% 	simulate_policy   Short description
% 		[] = simulate_policy(INPUT VARIABLES)
% 
% Arguments --------------------------------------------------------------
% Let iS = initial state, policy = policy to be executed,
%   P(SxSxA)  = transition matrix 
%              P could be an array with 3 dimensions or 
%              a cell array (1xA), each cell containing a matrix (SxS) possibly sparse,
%   R(SxSxA) or (SxA) or (S) = reward matrix
%              R could be an array with 3 dimensions (SxSxA) or 
%              a cell array (1xA), each cell containing a sparse matrix (SxS) or
%              a 2D array(SxA) possibly sparse or 
%              an array specifying the reward for reaching a certain state,
%   Rn(SxSxA) or (SxA) or (S) = normative reward matrix
%              R could be an array with 3 dimensions (SxSxA) or 
%              a cell array (1xA), each cell containing a sparse matrix (SxS) or
%              a 2D array(SxA) possibly sparse or 
%              an array specifying the reward for reaching a certain state, and
%   T		The time limit for the simulation
%   
% Evaluation -------------------------------------------------------------
%   PR(SxA)   = reward matrix

% 	
% 	Created by Felipe Meneguzzi on 2013-01-29.
% 	Copyright (c)  . All rights reserved.



% Initialize variables for stats collection
% TODO Design stats

% Initialize simulation
s = iS; % s contains the current state
S = size(P,1); % S is the number of states
normativeReward = 0;
individualReward = 0;

% Do the actual simulation
for t=1:T
	normativeReward = Rn(s);
	individualReward = R(s) + Rn(s);
	a = policy(s);
	% printf('Action chosen %s\n',actionName(a,['N','S','E','W']));
	p_s_new = rand(1);
	s_new = 0;
	p = 0;
	while( (p < p_s_new) && (s_new < S));
		s_new = s_new+1;
		p = p + P(s,s_new,a);
	end;
	% printf('Next state is %d\n',s_new);
	if(isterminal(P,s_new))
		printf('Current state %d is terminal, terminating simulation\n',s_new);
		break;
	end;
	s = s_new;
end

disp('Time limit reached, ending simulation \n');
printf('Normative Reward = %d\n',normativeReward);
printf('Individual Reward = %d\n',individualReward);

end %  function

function C = actionName(a,AN)
% 	actionName   Short description
% 		[C] = actionName(a,AN)
% 		where: a - action index; AN - action names
% 
% 	Long description
% 	
% 	Created by Felipe Meneguzzi on 2013-01-31.
% 	Copyright (c)  . All rights reserved.

	C = AN(a);
end %  function