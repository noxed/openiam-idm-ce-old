def output="(&(objectCategory=person)(objectClass=user))";
if(role != null) {
    output="(&(objectCategory=person)(objectClass=user))";
} else if(group != null) {
    output="(&(&(objectCategory=person)(objectClass=user))(memberOf="+ group.externalGroupName +"))";
}
