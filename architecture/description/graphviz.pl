%% represent jSeduite as a Graphviz code
%% author Sebastien Mosser <mosser@polytech.unice.fr>

% run :- [jSeduite], graphify, halt.

portray :- false.

run(G) :- 
	print('graph jSeduite {\n'),
	print('  label="jSeduite Architecture";\n'),
	print('  fontname="Courier";\n'),
	print('  overlap=scale;\n'),
	print('  node [style=filled, fontname="Courier", fontsize=12];\n'),
	apply(G,[]),
	print('}\n').

%%%%%%%%%%%%%%%%%%%%
%%% Usual Mixins %%%
%%%%%%%%%%%%%%%%%%%%

global_arch :-  graph_db, graph_ws, graph_libs, graph_orch,
	lnk_ws_lib, lnk_ws_db, lnk_orch_ws.
orch_arch :- graph_orch, graph_ws, lnk_orch_ws.
ws_arch :- graph_ws, graph_libs, lnk_ws_lib.
	

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%% Mixins functionalities %%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

graph_db :- 
	findall(X,generate_table(X),_),               % table/1
	findall(X,generate_view(X),_),                % view/1
	findall([X,Y],link_table_to_table(X,Y),_),    % references/2
	findall([X,Y],link_view_to_table(X,Y),_).     % references/2
	
graph_ws :- findall(X,generate_webservice(X),_).      % webservice/1

graph_libs :-  
	findall(X,generate_libraries(X),_),           % library/1
	findall([X,Y],link_lib_to_lib(X,Y),_).        % depends/2

graph_orch :- findall(X,generate_orchestration(X),_). % orchestration/1

lnk_ws_lib :- findall([X,Y],link_ws_to_lib(X,Y),_).   % loads/2
lnk_ws_db :- findall([X,Y],link_ws_to_table(X,Y),_).  % uses/2
lnk_orch_ws :- findall([X,Y],link_orch_to_ws(X,Y),_). % usesAsPartner/2


%%%%%%%%%%%%%%%%%%%%%%%%
%%% Dependency Graph %%%
%%%%%%%%%%%%%%%%%%%%%%%%

dgraph([]) :- !.
dgraph([H|T]) :- table(H), generate_table(H), findall(O,references(H,O),Dep),
	dgraph(Dep), dgraph(T).
dgraph([H|T]) :- view(H), generate_view(H), 
	findall(O,references(H,O),Dep), dlink_references(H,Dep), 
	dgraph(Dep), dgraph(T).	
dgraph([H|T]) :- library(H), generate_libraries(H), 
	findall(O, depends(H,O),Dep), dlink_depends(H,Dep), 
	dgraph(Dep), dgraph(T).
dgraph([H|T]) :- webservice(H), generate_webservice(H), 
	findall(O, uses(H,O), Tables), dlink_uses(H,Tables), dgraph(Tables),
	findall(O, loads(H,O), Libs), dlink_loads(H,Libs), dgraph(Libs),
	dgraph(T).
dgraph([H|T]) :- orchestration(H), generate_orchestration(H), 
	findall(O, usesAsPartner(H,O), Dep), dlink_partners(H,Dep),
	dgraph(Dep), dgraph(T).

dlink_references(_,[]).
dlink_references(E,[H|T]) :- link_table_to_table(E,H), dlink_references(E,T).
dlink_references(E,[H|T]) :- link_view_to_table(E,H), dlink_references(E,T).

dlink_depends(_,[]).
dlink_depends(E,[H|T]) :- link_lib_to_lib(E,H), dlink_depends(E,T).

dlink_uses(_,[]).
dlink_uses(E,[H|T]) :- link_ws_to_table(E,H), dlink_uses(E,T).

dlink_loads(_,[]).
dlink_loads(E,[H|T]) :- link_ws_to_lib(E,H), dlink_loads(E,T).

dlink_partners(_,[]).
dlink_partners(E,[H|T]) :- link_orch_to_ws(E,H), dlink_partners(E,T). 


%%%%%%%%%%%%%%%%%%%%%%%
%%% Atomic Tool Box %%%
%%%%%%%%%%%%%%%%%%%%%%%


%%% BOXES 
generate_webservice(X) :- 
	webservice(X), print('  '), print(X), print('_ws ['),
	print('label="'), print(X), print('", shape=ellipse, '),
	print('fillcolor="#FFF8DC"];\n').

generate_libraries(X) :-
	library(X), print('  '), print(X), print('_lib ['),
	print('label="'),print(X),print('", shape=egg, fillcolor="#FFF0F5"'),
	print('];\n').

generate_table(X) :-
	table(X), print('  '), print(X), print('_db [label="'),print(X),
	print('", shape=box, fillcolor="#D3D3D3"];\n').

generate_view(X) :- 
	view(X), print('  '), print(X), print('_db [label="'),print(X),
	print('", shape=box, fillcolor="#778899"];\n').

generate_orchestration(X) :- 
	orchestration(X), print('  '), print(X), print('_ws [label="'),
	print(X), print('", shape=octagon, fillcolor="#FFDEAD"]; \n').

%%% Arrows 

link_ws_to_lib(Ws,Lib) :- 
	webservice(Ws), library(Lib), loads(Ws,Lib),
	print('  '), print(Ws), print('_ws -- '), print(Lib), print('_lib ;\n').

link_lib_to_lib(X,Y) :-
	library(X), library(Y), depends(X,Y), 
	print('  '), print(X), print('_lib -- '), print(Y), print('_lib ;\n').

link_table_to_table(X,Y) :- table(X), link_thing_to_table(X,Y).
link_view_to_table(X,Y) :- view(X), link_thing_to_table(X,Y).
link_thing_to_table(X,Y) :- 
	table(Y), references(X,Y), print('  '), print(X),print('_db -- '), 
	print(Y), print('_db;\n').

link_ws_to_table(X,Y) :- 
	webservice(X), uses(X,Y), print('  '), print(X), print('_ws -- '),
	print(Y), print('_db;\n').

link_orch_to_ws(X,Y) :-
	orchestration(X), usesAsPartner(X,Y), print('  '),
	print(X),print('_ws -- '), print(Y), print('_ws ;\n').