all:
	scc pd_mix -sc2out -vv
	scc pd_custCh -sc2out -vv
test:
	./pd_mix 
	./pd_custCh
clean:
	rm -f *.cc *.h *.o *.si
	rm -f pd_mix pd_custCh
