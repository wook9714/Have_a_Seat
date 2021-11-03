package kr.co.jinwook.have_a_seat

data class DataForRestaurantInfoActivity(val a:Int = 3)


data class RoomData(var roomInfo:String?=null,var test:Int = 0,
                    val joinedUser:MutableList<JoinedUserInfoForBill> = mutableListOf()
)

data class UserData(var name: String? = null,
                    var age:Int? = null,
                    var birthDay:Int? = null
)

data class JoinedUserInfoForBill(var id:String,val name:String,val oderedFood:MutableList<FoodDataForBill> = mutableListOf())


data class FoodDataForBill(val id:String,
                           val photoId:String,
                           val foodName:String,
                           val price:Int
)
