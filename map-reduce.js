var mapFunction2 = function(){
for (var idx = 0; idx < this.items.length; idx++){
var key = this.items[idx].sku;
var value =
{
count: 1,
qty: this.items[idx].qty
}
;
emit(key, value);
}
}
;