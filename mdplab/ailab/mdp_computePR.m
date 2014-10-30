function PR = mdp_computePR(P,R)


% mdp_computePR  Computes the reward for the system in one state 
%                chosing an action
% Arguments --------------------------------------------------------------
% Let S = number of states, A = number of actions
%   P(SxSxA)  = transition matrix 
%              P could be an array with 3 dimensions or 
%              a cell array (1xA), each cell containing a matrix (SxS) possibly sparse
%   R(SxSxA) or (SxA) or (S) = reward matrix
%              R could be an array with 3 dimensions (SxSxA) or 
%              a cell array (1xA), each cell containing a sparse matrix (SxS) or
%              a 2D array(SxA) possibly sparse or 
%              an array specifying the reward for reaching a certain state
%   
% Evaluation -------------------------------------------------------------
%   PR(SxA)   = reward matrix


% =======================
% = Based on MDPtoolbox =
% =======================
% MDPtoolbox: Markov Decision Processes Toolbox
% Copyright (C) 2009  INRA
% Redistribution and use in source and binary forms, with or without modification, 
% are permitted provided that the following conditions are met:
%    * Redistributions of source code must retain the above copyright notice, 
%      this list of conditions and the following disclaimer.
%    * Redistributions in binary form must reproduce the above copyright notice, 
%      this list of conditions and the following disclaimer in the documentation 
%      and/or other materials provided with the distribution.
%    * Neither the name of the <ORGANIZATION> nor the names of its contributors 
%      may be used to endorse or promote products derived from this software 
%      without specific prior written permission.
% THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
% ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
% WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
% IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
% INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
% BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
% DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
% LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
% OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
% OF THE POSSIBILITY OF SUCH DAMAGE.

if ndims(R)==2 && ~iscell(R)  % R has form R(SxA) 
	PR = R;
	if size(R,2)==1 % R has the form R(S)
		if iscell(P)
			A = length(P);
			% TODO Learn to use cell matrices
			% for a=1:A
			% 			for s=1:length(P{a})
			% 			PR(,a)
			% end
			error('Cannot deal with cell matrices');
		else
			A = size(P,3);
			PR = zeros(size(P,1),A);
			for a=1:A
				for s=1:size(P,1)
					if(isterminal(P,s))
						PR(s,a) = R(s);
					else
						PR(s,a) = sum(P(s,:,a).*transpose(R));
					end
				end
			end
		end
	end
else % R has form R(SxSxA)
    PR = [];
    if iscell(P)
        A = length(P);
        if iscell(R)
            for a=1:A; PR(:,a) = sum(P{a}.*R{a},2); end;
        else
            for a=1:A; PR(:,a) = sum(P{a}.*R(:,:,a),2); end;
        end
    else
        A = size(P,3);
        if iscell(R)
            for a=1:A; PR(:,a) = sum(P(:,:,a).*R{a},2); end;
        else
            for a=1:A; PR(:,a) = sum(P(:,:,a).*R(:,:,a),2); end;
        end
    end
end;
