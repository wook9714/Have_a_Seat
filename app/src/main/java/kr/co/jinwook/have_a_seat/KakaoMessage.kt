package kr.co.jinwook.have_a_seat

import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.link.LinkClient
import com.kakao.sdk.link.WebSharerClient
import com.kakao.sdk.template.model.*


object KakaoMessage {

    fun sendMessage(context:Context, feedTemplate: FeedTemplate){
        // 피드 메시지 보내기 두번째 파라미터로 보낼 템플릿 선택

        // 카카오톡 설치여부 확인
        if (LinkClient.instance.isKakaoLinkAvailable(context)) {
            // 카카오톡으로 카카오링크 공유 가능
            LinkClient.instance.defaultTemplate(context, feedTemplate) { linkResult, error ->
                if (error != null) {
                    Log.e(TAG, "카카오링크 보내기 실패", error)
                }
                else if (linkResult != null) {
                    Log.d(TAG, "카카오링크 보내기 성공 ${linkResult.intent}")
                    context.startActivity(linkResult.intent)

                    // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Log.w(TAG, "Warning Msg: ${linkResult.warningMsg}")
                    Log.w(TAG, "Argument Msg: ${linkResult.argumentMsg}")
                }
            }
        } else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            // 웹 공유 예시 코드
            val sharerUrl = WebSharerClient.instance.defaultTemplateUri(feedTemplate)

            // CustomTabs으로 웹 브라우저 열기

            // 1. CustomTabs으로 Chrome 브라우저 열기
            try {
                KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
            } catch(e: UnsupportedOperationException) {
                // Chrome 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabs으로 디바이스 기본 브라우저 열기
            try {
                KakaoCustomTabsClient.open(context, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                // 인터넷 브라우저가 없을 때 예외처리
            }
        }
    }




    val defaultFeed = FeedTemplate(
        content = Content(
            title = "오늘의 디저트",
            description = "#케익 #딸기 #삼평동 #카페 #분위기 #소개팅",
            imageUrl = "http://mud-kage.kakao.co.kr/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
            link = Link(
                webUrl = "https://developers.kakao.com",
                mobileWebUrl = "https://developers.kakao.com"
            )
        ),
        itemContent = ItemContent(
            profileText = "Kakao",
            profileImageUrl = "http://mud-kage.kakao.co.kr/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
            titleImageUrl = "http://mud-kage.kakao.co.kr/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
            titleImageText = "Cheese cake",
            titleImageCategory = "Cake",
            items = listOf(
                ItemInfo(item = "cake1", itemOp = "1000원"),
                ItemInfo(item = "cake2", itemOp = "2000원"),
                ItemInfo(item = "cake3", itemOp = "3000원"),
                ItemInfo(item = "cake4", itemOp = "4000원"),
                ItemInfo(item = "cake5", itemOp = "5000원")
            ),
            sum = "Total",
            sumOp = "15000원"
        ),
        social = Social(
            likeCount = 286,
            commentCount = 45,
            sharedCount = 845
        ),
        buttons = listOf(
            Button(
                "웹으로 보기",
                Link(
                    webUrl = "https://developers.kakao.com",
                    mobileWebUrl = "https://developers.kakao.com"
                )
            ),
            Button(
                "앱으로 보기",
                Link(
                    androidExecutionParams = mapOf("key1" to "value1", "key2" to "value2"),
                    iosExecutionParams = mapOf("key1" to "value1", "key2" to "value2")
                )
            )
        )
    )
}