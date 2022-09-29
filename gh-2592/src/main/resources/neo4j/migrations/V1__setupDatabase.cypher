MATCH (n) detach delete n;

with [{shape:"hktxjm.zhanLue",appType:"ceDu",defaultName:"测度",name:"testCeDu1",description:"test",`datas.extendField`:"test",idd:"node-6edd138d-03e6-4b15-8cd2-4c2e930b75f1",nodeVersion:"1",model:"hktxjmmodel9",nodeStatu:"ACTIVE"},{shape:"hktxjm.zhanLue",appType:"ceDu",defaultName:"测度",name:"testCeDu2",description:"test2",`datas.extendField`:"test2",idd:"node-7c067fa8-50f7-47df-b43a-1e41c0c4006d",nodeVersion:"1",model:"hktxjmmodel9",nodeStatu:"ACTIVE"},{shape:"hktxjm.yingYong",appType:"yingYongY",defaultName:"域",name:"testYingYongY1",idd:"node-41498c56-7f82-4444-927a-39e73fc1bfa1",nodeVersion:"1",model:"hktxjmmodel9",nodeStatu:"ACTIVE"}] as maps, 'hktxjmmodel9' as model
unwind maps as map
MERGE (s:hktxjm { model:model, idd:map.idd, nodeStatu:'ACTIVE' })
ON CREATE SET s = map, s.model = model
ON MATCH SET s += map
RETURN s;

with [{sourceNodeIdd:"node-6edd138d-03e6-4b15-8cd2-4c2e930b75f1",targetNodeIdd:"node-41498c56-7f82-4444-927a-39e73fc1bfa1",edgeIdd:"edge-93e6c4f8-f06e-4136-835a-69d7d5f5696b",edgeMap:{shape:"hktxjm.guanXi",appType:"parent",defaultName:"父",name:"testParent1",idd:"edge-93e6c4f8-f06e-4136-835a-69d7d5f5696b"}},{sourceNodeIdd:"node-7c067fa8-50f7-47df-b43a-1e41c0c4006d",targetNodeIdd:"node-41498c56-7f82-4444-927a-39e73fc1bfa1",edgeIdd:"edge-7503965e-c9fa-4547-aba1-6a6df16c67cd",edgeMap:{shape:"hktxjm.guanXi",appType:"parent",defaultName:"父",name:"testParent2",idd:"edge-7503965e-c9fa-4547-aba1-6a6df16c67cd"}}] as lists, 'hktxjmmodel9' as model
 unwind lists as item
 MATCH (s:hktxjm { model:model, idd:item.sourceNodeIdd, nodeStatu:'ACTIVE' })
 MATCH (t:hktxjm { model:model, idd:item.targetNodeIdd, nodeStatu:'ACTIVE' })
 MERGE (s)-[r:guanXi {idd:item.edgeIdd}]->(t)
 ON CREATE SET r = item.edgeMap
 ON MATCH SET r += item.edgeMap
 WITH item,model,s,r,t
 MATCH(s2:hktxjm { model:model})-[r2:guanXi {idd:item.edgeIdd}]->(t2:hktxjm { model:model})
 WHERE s.idd <> s2.idd or t.idd <> t2.idd
 DELETE r2
 RETURN s,collect(r),collect(t);