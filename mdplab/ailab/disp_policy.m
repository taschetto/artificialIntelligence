function [] = disp_policy(AN,A,gsize)
% 	GRID_DISP_POLICY   Prints a human readable policy with the given names.
% 		[[]] = GRID_DISP_POLICY(AN,A)
% 
% 	Where: AN is a character array with the names corresponding to the action indexes
% 	       A is the policy which we want to print
% 	
% 	Created by Felipe Meneguzzi on 2013-01-02.
% 	Copyright (c)  . All rights reserved.

Names = char(zeros(size(A,1),1));
for p = [1:size(A)]
	% fprintf('%s \n',AN(A(p)));
	Names(p) = AN(A(p));
end

% N = vec2mat(Names,gsize(1));
N = reshape(Names,gsize(1),gsize(2));
disp(N);

end %  function