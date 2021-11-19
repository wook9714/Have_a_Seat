package kr.co.jinwook.have_a_seat

import net.daum.mf.map.api.MapPoint

data class UserInfo(var name:String?=null,
                    var phoneNumber:String? = null,
                    
                    //현재 선택된 장소의 위치 저장
                    var selectedAddress:address? = null,
                    
                    //현재까지 주문했던 장소의 위치저장
                    var userAddressList:MutableList<address>
                    )

data class address(val point: MapPoint, val detailedAddress:String)
