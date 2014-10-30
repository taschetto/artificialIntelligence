function [P,R] = gen_scenario(scenario, args)
% 	GEN_SCENARIO   Generates the specified MDP scenario with the specified parameters
% 		[P,R] = gen_scenario(scenario, args)
%   
%   Parameters 
%   scenario -- a string 
%   args -- a structure with the following components:
%           - gsize -- a vector with the grid dimensions
%           - obstacles -- a cell array of [i,j] coordinates of the obstacles in the grid
%           - terminals -- a cell array of [i,j] terminal states in the grid
%           - Pm -- the Probability of successfully moving
%           - rewards -- a cell array of [i,j,r] rewards for specific states
%           - R -- the default reward for all nonspecified states
% 	Always remember that in Matlab single subscript indexing results in the matrix 
% 	being indexed as a long COLUMN vector!
% 	
% 	Created by Felipe Meneguzzi on 2012-12-28.
% 	Copyright (c)  . All rights reserved.

% ============================
% = The names of the actions =
% ============================
actions.North = 1; 
actions.South = 2; 
actions.East = 3; 
actions.West = 4;

switch scenario
case 'aima'
	Pm = args.Pm;
	% Pm = .8; %Prob move
	Ps = (1-Pm)/2; %Prob of ending to the sides

	% S = gsize(1)*gsize(2);
	S = 12;
	P = zeros(S,S,4);
	R = zeros(S,4);

	[P,R] = aima_scenario(S,Pm,Ps,actions);
case 'salesman'
	% [P,R] = salesman_scenario(args.size, 1, .5)
	[P,R] = salesman_scenario(args.size, args.Pm, args.Ps)	
case 'grid'
	% TODO Finish implementing a generic environment generation 
	S = args.gsize(1)*args.gsize(2);
	
	P = zeros(S,S,4);
	
	Pm = args.Pm; %Prob move
	Ps = (1-Pm)/2; %Prob of ending to the sides
	
	for A = 1:4
		% fprintf('Grid is %d by %d \n',args.gsize(1),args.gsize(2));
		% fprintf('Action %d \n',A);
		for I = 1:args.gsize(1)
			for J = 1:args.gsize(2)
				% disp('From')
				% disp([I,J]);
				if(contains([I,J], args.obstacles))
					% disp('Is obstacle itself, skipping');
					continue;
				end
				
				if(contains([I,J], args.terminals))
					% disp('Is terminal state, skipping');
					continue;
				end
				
				Sfrom = sub2ind(args.gsize,I,J);
				D = front(A,I,J,actions);
				ti = D(1);
				tj = D(2);
				% If the destination of the move is out of the grid, add Pm to self transition
				if(valid(ti,tj,args))
					Sto = sub2ind(args.gsize,ti,tj);
					P(Sfrom,Sto,A) = Pm;
				else
					P(Sfrom,Sfrom,A) = Pm;
				end
				
				% If any of the sides of the move are out of the grid, add Ps to self transition
				D = left(A,I,J,actions);
				ti = D(1);
				tj = D(2);
				if(valid(ti,tj,args))
					Sto = sub2ind(args.gsize,ti,tj);
					P(Sfrom,Sto,A) = Ps;
				else
					P(Sfrom,Sfrom,A) = P(Sfrom,Sfrom,A) + Ps;
				end
				
				D = right(A,I,J,actions);
				ti = D(1);
				tj = D(2);
				if(valid(ti,tj,args))
					Sto = sub2ind(args.gsize,ti,tj);
					P(Sfrom,Sto,A) = Ps;
				else
					P(Sfrom,Sfrom,A) = P(Sfrom,Sfrom,A) + Ps;
				end
				% disp('***************');
			end
		end
	end
	R = ones(S,1);
	R = R*args.R;
	for I=1:size(args.rewards,2)
		Si = args.rewards{I}(1);
		Sj = args.rewards{I}(2);
		Sv = args.rewards{I}(3);
		SR = sub2ind(args.gsize, Si,Sj);
		R(SR)=Sv;
	end
end

end %  function

% Returns wether or not the specified grid position is valid
function valid = valid(I,J,args)
	% First check that I, J are in the bounds of the grid
	valid = ((I > 0) && (I <= args.gsize(1)) && (J > 0) && (J <= args.gsize(2)) );
	valid = valid && not(contains([I,J], args.obstacles));
end

function M = contains(V,S)
	for I=1:size(S,2)
		if (S{I}(1) == V(1)) && (S{I}(2) == V(2))
			M = 1;
			return;
		end
	end
	M = 0;
end

% Returns the "left" position of the specified position given Action
function D = left(Action,i,j,actions)
	switch Action
	case actions.North
		D = [i,j-1];
	case actions.South
		D = [i,j+1];
	case actions.East
		D = [i-1,j];
	case actions.West
		D = [i+1,j];
	otherwise
		error('Invalid action ',Action);
	end
	% disp('To Left');
	% 	disp(D);
end

% Returns the "right" position of the specified position given Action
function D = right(Action,i,j,actions)
	switch Action
	case actions.North
		D = [i,j+1];
	case actions.South
		D = [i,j-1];
	case actions.East
		D = [i+1,j];
	case actions.West
		D = [i-1,j];
	otherwise
		error('Invalid action ',Action);
	end
	% disp('To Right');
	% disp(D);
end

% Returns the "front" position of the specified position given Action
function D = front(Action,i,j,actions)
	switch Action
	case actions.North
		D = [i-1,j];
	case actions.South
		D = [i+1,j];
	case actions.East
		D = [i,j+1];
	case actions.West
		D = [i,j-1];
	otherwise
		error('Invalid action ',Action);
	end
	% disp('To Front');
	% disp(D);
end

function [P,R] = salesman_scenario(S, Pm, Ps)
	P = zeros(3,3,3);
	P(1,1,1) = Ps;
	P(2,2,1) = 1;
	P(3,3,1) = 1;

	P(1,2,2) = Pm;
	P(1,1,2) = 1 - Pm;
	P(3,1,2) = Pm;
	P(3,3,2) = 1 -Pm;

	P(1,3,3) = Pm;
	P(1,1,3) = 1 - Pm;
	P(2,1,3) = Pm;
	P(2,2,3) = 1-Pm;

	R = zeros(3,3);
	R(1,1) = 1;
	R(2,1) = 2;
	R(3,1) = 1;
	% R = zeros(1,3);
	% R(1) = 1;
	% R(2) = 2;
	% R(3) = 1;

	% R(:,2) = 0;
	% R(:,3) = 0;
end;

function [P,R] = aima_scenario(S,Pm,Ps,actions)
% 	HARDCODED_SCENARIO   Generates a hardcoded scenario according to the AIMA Book
% 		[P,R] = HARDCODED_SCENARIO(S, Pm, Ps)
% 
% 	Long description
% 	
% 	Created by Felipe Meneguzzi on 2012-12-28.
% 	Copyright (c)  . All rights reserved.

% ============================
% = The names of the actions =
% ============================

P = zeros(S,S,4);
R = zeros(S,1);

P(1,1,actions.North) = Pm + Ps;
P(1,4,actions.North) = Ps;

P(4,4,actions.North) = Pm;
P(4,1,actions.North) = Ps;
P(4,7,actions.North) = Ps;

P(7,7,actions.North) = Pm;
P(7,4,actions.North) = Ps;
P(7,10,actions.North) = Ps;

P(10,10,actions.North) = Pm+Ps;
P(10,7,actions.North) = Ps;

P(2,2,actions.North) = Ps + Ps;
P(2,1,actions.North) = Pm;

P(8,8,actions.North) = Ps;
P(8,7,actions.North) = Pm;
P(8,11,actions.North) = Ps;

P(11,11,actions.North) = Ps;
P(11,8,actions.North) = Ps;
P(11,10,actions.North) = Pm;

P(3,3,actions.North) = Ps;
P(3,6,actions.North) = Ps;
P(3,2,actions.North) = Pm;

P(6,6,actions.North) = Pm;
P(6,3,actions.North) = Ps;
P(6,9,actions.North) = Ps;

P(9,8,actions.North) = Pm;
P(9,6,actions.North) = Ps;
P(9,12,actions.North) = Ps;

P(12,12,actions.North) = Ps;
P(12,11,actions.North) = Pm;
P(12,9,actions.North) = Ps;

% actions.South

P(1,1,actions.South) = Ps;
P(1,4,actions.South) = Ps;
P(1,2,actions.South) = Pm;

P(4,4,actions.South) = Pm;
P(4,1,actions.South) = Ps;
P(4,7,actions.South) = Ps;

P(7,4,actions.South) = Ps;
P(7,10,actions.South) = Ps;
P(7,8,actions.South) = Pm;

P(10,10,actions.South) = Ps;
P(10,7,actions.South) = Ps;
P(10,11,actions.South) = Pm;

P(2,2,actions.South) = Ps + Ps;
P(2,3,actions.South) = Pm;

P(8,8,actions.South) = Ps;
P(8,11,actions.South) = Ps;
P(8,9,actions.South) = Pm;

P(11,11,actions.South) = Ps;
P(11,8,actions.South) = Ps;
P(11,12,actions.South) = Pm;

P(3,3,actions.South) = Pm+Ps;
P(3,6,actions.South) = Ps;

P(6,6,actions.South) = Pm;
P(6,3,actions.South) = Ps;
P(6,9,actions.South) = Ps;

P(9,9,actions.South) = Pm;
P(9,6,actions.South) = Ps;
P(9,12,actions.South) = Ps;

P(12,12,actions.South) = Pm+Ps;
P(12,9,actions.South) = Ps;

% actions.West

P(1,1,actions.West) = Ps+Pm;
P(1,2,actions.West) = Ps;

P(4,4,actions.West) = Ps+Ps;
P(4,1,actions.West) = Pm;

P(7,4,actions.West) = Pm;
P(7,7,actions.West) = Ps;
P(7,8,actions.West) = Ps;

P(10,7,actions.West) = Pm;
P(10,10,actions.West) = Ps;
P(10,11,actions.West) = Ps;

P(2,2,actions.West) = Pm;
P(2,1,actions.West) = Ps;
P(2,3,actions.West) = Ps;

P(8,8,actions.West) = Pm;
P(8,7,actions.West) = Ps;
P(8,9,actions.West) = Ps;

P(11,8,actions.West) = Pm;
P(11,10,actions.West) = Ps;
P(11,12,actions.West) = Ps;

P(3,3,actions.West) = Pm+Ps;
P(3,2,actions.West) = Ps;

P(6,3,actions.West) = Pm;
P(6,6,actions.West) = Ps + Ps;

P(9,6,actions.West) = Pm;
P(9,9,actions.West) = Ps;
P(9,8,actions.West) = Ps;

P(12,9,actions.West) = Pm;
P(12,11,actions.West) = Ps;
P(12,12,actions.West) = Ps;

% actions.East

P(1,4,actions.East) = Pm;
P(1,1,actions.East) = Ps;
P(1,2,actions.East) = Ps;

P(4,7,actions.East) = Pm;
P(4,4,actions.East) = Ps+Ps;

P(7,10,actions.East) = Pm;
P(7,7,actions.East) = Ps;
P(7,8,actions.East) = Ps;

P(10,10,actions.East) = Pm+Ps;
P(10,11,actions.East) = Ps;

P(2,2,actions.East) = Pm;
P(2,1,actions.East) = Ps;
P(2,3,actions.East) = Ps;

P(8,11,actions.East) = Pm;
P(8,7,actions.East) = Ps;
P(8,9,actions.East) = Ps;

P(11,11,actions.East) = Pm;
P(11,10,actions.East) = Ps;
P(11,12,actions.East) = Ps;

P(3,6,actions.East) = Pm;
P(3,3,actions.East) = Ps;
P(3,2,actions.East) = Ps;

P(6,9,actions.East) = Pm;
P(6,6,actions.East) = Ps+Ps;

P(9,12,actions.East) = Pm;
P(9,9,actions.East) = Ps;
P(9,8,actions.East) = Ps;

P(12,12,actions.East) = Pm+Ps;
P(12,11,actions.East) = Ps;

R = ones(S,1);
R = R * -1;

R(10) = 100;
R(11) = -100;

P(11,:,:) = P(11,:,:)*0;
P(10,:,:) = P(10,:,:)*0;

end %  function