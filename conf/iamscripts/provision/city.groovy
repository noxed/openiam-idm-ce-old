
def it = user.addresses?.iterator()
output = it?.hasNext() ? it.next()?.city : null
