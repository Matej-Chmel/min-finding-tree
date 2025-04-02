graph TD
node0{ 0 < 1 }
node1[/ 0 /]
node0 -- true --> node1
node2{ 0 == 1 }
node3[/ 0, 1 /]
node2 -- true --> node3
node4[/ 1 /]
node2 -- false --> node4
node0 -- false --> node2
