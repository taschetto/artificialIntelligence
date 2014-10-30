function terminal = isterminal(P,s)
% 	TERMINAL   Returns whether or not s is a terminal state in P
% 		[TERMINAL] = TERMINAL(P,S)
% 
% 	Long description
% 	
% 	Created by Felipe Meneguzzi on 2012-12-28.
% 	Copyright (c)  . All rights reserved.

if sum(P(s,:,:))==0
	terminal = true;
else
	terminal = false;
end

end %  function