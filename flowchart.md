graph TD
node0{ 0 < 1 }
node1{ 2 < 0 }
node2{ 3 < 2 }
node3[/ 3 /]
node2 -- true --> node3
node4{ 3 == 2 }
node5[/ 2, 3 /]
node4 -- true --> node5
node6[/ 2 /]
node4 -- false --> node6
node2 -- false --> node4
node1 -- true --> node2
node7{ 2 == 0 }
node8{ 3 < 0 }
node9[/ 3 /]
node8 -- true --> node9
node10{ 3 == 0 }
node11[/ 0, 2, 3 /]
node10 -- true --> node11
node12[/ 0, 2 /]
node10 -- false --> node12
node8 -- false --> node10
node7 -- true --> node8
node13{ 3 < 0 }
node14[/ 3 /]
node13 -- true --> node14
node15{ 3 == 0 }
node16[/ 0, 3 /]
node15 -- true --> node16
node17[/ 0 /]
node15 -- false --> node17
node13 -- false --> node15
node7 -- false --> node13
node1 -- false --> node7
node0 -- true --> node1
node18{ 0 == 1 }
node19{ 2 < 0 }
node20{ 3 < 2 }
node21[/ 3 /]
node20 -- true --> node21
node22{ 3 == 2 }
node23[/ 2, 3 /]
node22 -- true --> node23
node24[/ 2 /]
node22 -- false --> node24
node20 -- false --> node22
node19 -- true --> node20
node25{ 2 == 0 }
node26{ 3 < 0 }
node27[/ 3 /]
node26 -- true --> node27
node28{ 3 == 0 }
node29[/ 0, 1, 2, 3 /]
node28 -- true --> node29
node30[/ 0, 1, 2 /]
node28 -- false --> node30
node26 -- false --> node28
node25 -- true --> node26
node31{ 3 < 0 }
node32[/ 3 /]
node31 -- true --> node32
node33{ 3 == 0 }
node34[/ 0, 1, 3 /]
node33 -- true --> node34
node35[/ 0, 1 /]
node33 -- false --> node35
node31 -- false --> node33
node25 -- false --> node31
node19 -- false --> node25
node18 -- true --> node19
node36{ 2 < 1 }
node37{ 3 < 2 }
node38[/ 3 /]
node37 -- true --> node38
node39{ 3 == 2 }
node40[/ 2, 3 /]
node39 -- true --> node40
node41[/ 2 /]
node39 -- false --> node41
node37 -- false --> node39
node36 -- true --> node37
node42{ 2 == 1 }
node43{ 3 < 1 }
node44[/ 3 /]
node43 -- true --> node44
node45{ 3 == 1 }
node46[/ 1, 2, 3 /]
node45 -- true --> node46
node47[/ 1, 2 /]
node45 -- false --> node47
node43 -- false --> node45
node42 -- true --> node43
node48{ 3 < 1 }
node49[/ 3 /]
node48 -- true --> node49
node50{ 3 == 1 }
node51[/ 1, 3 /]
node50 -- true --> node51
node52[/ 1 /]
node50 -- false --> node52
node48 -- false --> node50
node42 -- false --> node48
node36 -- false --> node42
node18 -- false --> node36
node0 -- false --> node18
