package com.survivalcoding.ifkakao.data.datasource

import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class IkContentsDataSourceTest {
    private lateinit var service: IkContentsService
    private lateinit var dataSource: IkContentsDataSource

    @Before
    fun setUp() {
        service = RetrofitClient.getClient().create(IkContentsService::class.java)
        dataSource = IkContentsDataSource(service)
    }

    @Test
    fun getContents() = runBlocking {
        val content = dataSource.getContents()
        assertEquals(true, content.success)
        assertEquals(null, content.errorMessage)
        assertEquals(120, content.count)
        assertEquals(120, content.data?.size)

        val list = content.data ?: listOf()
//        assertEquals(0, list.filter { it.categoryIdx == null }.size)
//        assertEquals(0, list.filter { it.commentYn == null }.size)
        assertEquals(0, list.filter { it.company == null }.size)
//        assertEquals(0, list.filter { it.companyName == null }.size)
        assertEquals(0, list.filter { it.content == null }.size)
        assertEquals(0, list.filter { it.contentTag == null }.size)
        assertEquals(0, list.filter { it.contentsSpeakerList == null }.size)
//        assertEquals(0, list.filter { it.createdDateTime == null }.size)
//        assertEquals(0, list.filter { it.createdUserIdx == null }.size)
//        assertEquals(0, list.filter { it.favoriteYn == null }.size)
        assertEquals(0, list.filter { it.field == null }.size)
        assertEquals(0, list.filter { it.idx == null }.size)
//        assertEquals(0, list.filter { it.lastModifiedDateTime == null }.size)
//        assertEquals(0, list.filter { it.lastModifiedUserIdx == null }.size)
        assertEquals(0, list.filter { it.linkList == null }.size)
//        assertEquals(0, list.filter { it.newCountentsYn == null }.size)

        // qna null -> 44
//        assertEquals(44, list.filter { it.qnaEndTime == null }.size)
//        assertEquals(44, list.filter { it.qnaStartDay == null }.size)
//        assertEquals(44, list.filter { it.qnaStartTime == null }.size)

        assertEquals(0, list.filter { it.relationList == null }.size)

        // reservation null -> 1
//        assertEquals(1, list.filter { it.reservationDate == null }.size)
//        assertEquals(1, list.filter { it.reservationTime == null }.size)
//        assertEquals(1, list.filter { it.reservationUTC == null }.size)
//        assertEquals(0, list.filter { it.reservationYn == null }.size)

//        assertEquals(0, list.filter { it.sessionType == null }.size)
//        assertEquals(0, list.filter { it.speakerLoginYn == null }.size)
//        assertEquals(0, list.filter { it.speakerName == null }.size)
        assertEquals(0, list.filter { it.spotlightYn == null }.size)
        assertEquals(0, list.filter { it.title == null }.size)
//        assertEquals(0, list.filter { it.updateCountentsYn == null }.size)
        assertEquals(0, list.filter { it.videoYn == null }.size)

        // link list test
//        assertEquals(0, list.filter { AnnotationTarget.FILE == null }.size)
//        assertEquals(0, list.filter { it.linkList?.MO_IMAGE == null }.size)
//        assertEquals(0, list.filter { it.linkList?.MO_MAIN_IMAGE == null }.size)
//        assertEquals(0, list.filter { it.linkList?.MO_SPOTLIGHT == null }.size)
        assertEquals(0, list.filter { it.linkList?.PC_IMAGE == null }.size)
//        assertEquals(0, list.filter { it.linkList?.PC_MAIN_IMAGE == null }.size)
//        assertEquals(0, list.filter { it.linkList?.PC_SPOTLIGHT == null }.size)
//        assertEquals(0, list.filter { it.linkList?.SHARE_IMAGE == null }.size)
        assertEquals(0, list.filter { it.linkList?.SPEAKER_PROFILE == null }.size)
        assertEquals(0, list.filter { it.linkList?.VIDEO == null }.size)

//        assertEquals(30, list.filter { AnnotationTarget.FILE?.isEmpty() ?: true }.size)
//        assertEquals(0, list.filter { it.linkList?.MO_IMAGE?.isEmpty() ?: true }.size)
//        assertEquals(96, list.filter { it.linkList?.MO_MAIN_IMAGE?.isEmpty() ?: true }.size)
//        assertEquals(104, list.filter { it.linkList?.MO_SPOTLIGHT?.isEmpty() ?: true }.size)
        assertEquals(0, list.filter { it.linkList?.PC_IMAGE?.isEmpty() ?: true }.size)
//        assertEquals(96, list.filter { it.linkList?.PC_MAIN_IMAGE?.isEmpty() ?: true }.size)
//        assertEquals(104, list.filter { it.linkList?.PC_SPOTLIGHT?.isEmpty() ?: true }.size)
//        assertEquals(0, list.filter { it.linkList?.SHARE_IMAGE?.isEmpty() ?: true }.size)
        assertEquals(0, list.filter { it.linkList?.SPEAKER_PROFILE?.isEmpty() ?: true }.size)
        assertEquals(0, list.filter { it.linkList?.VIDEO?.isEmpty() ?: true }.size)
        assertEquals(0, list.filter { it.linkList?.VIDEO.size > 1 }.size)

        assertEquals(1, list.filter { it.linkList.PC_IMAGE.size > 1 }.size)
        var sb = StringBuilder()
        list.filter { it.linkList.PC_IMAGE.size > 1 }.forEach {
            sb.append(it.title).append(" ${it.linkList.PC_IMAGE.size}").append('\n')
        }
//        assertEquals(2, sb.toString())

//        assertEquals(120, list.filter { it.linkList?.MO_IMAGE?.first()?.url != null }.size)
        assertEquals(120, list.filter { it.linkList?.PC_IMAGE?.first()?.url != null }.size)

//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.contentsIdx == null } }.size)
//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.description == null } }.size)
//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.fileSize == null } }.size)
//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.idx == null } }.size)
//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.mainYn == null } }.size)
//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.type == null } }.size)
//        assertEquals(0, list.filter { it.linkList.MO_IMAGE.any { it.url == null } }.size)

        assertEquals(1, list.filter { it.videoYn != "Y" }.size)
        sb = StringBuilder()
        list.filter { it.videoYn != "Y" }.forEach {
            sb.append(it.title).append(" ${it.field}").append('\n')
        }
//        assertEquals("", sb.toString())
        assertEquals(null, list.first { it.videoYn != "Y" }.linkList.VIDEO.first().description)

        assertEquals(0, list.filter { it.linkList.PC_IMAGE.any { it.description == null } }.size)
//        assertEquals(0, list.filter { it.linkList.PC_IMAGE.any { it.fileSize == null } }.size)
//        assertEquals(0, list.filter { it.linkList.PC_IMAGE.any { it.idx == null } }.size)
//        assertEquals(0, list.filter { it.linkList.PC_IMAGE.any { it.mainYn == null } }.size)
//        assertEquals(0, list.filter { it.linkList.PC_IMAGE.any { it.type == null } }.size)
        assertEquals(0, list.filter { it.linkList.PC_IMAGE.any { it.url == null } }.size)

        assertEquals(3, list.filter { it.linkList.VIDEO.any { it.description == null } }.size)
        sb = StringBuilder()
        list.filter { it.linkList.VIDEO.any { it.description == null } }.forEach {
            sb.append(it.title).append(" ${it.videoYn}").append('\n')
        }
//        assertEquals("", sb.toString())

//        assertEquals(0, list.filter { it.linkList.VIDEO.any { it.fileSize == null } }.size)
//        assertEquals(0, list.filter { it.linkList.VIDEO.any { it.idx == null } }.size)
//        assertEquals(0, list.filter { it.linkList.VIDEO.any { it.mainYn == null } }.size)
//        assertEquals(0, list.filter { it.linkList.VIDEO.any { it.type == null } }.size)
        assertEquals(0, list.filter { it.linkList.VIDEO.any { it.url == null } }.size)

        assertEquals(
            0,
            list.filter { it.linkList?.SPEAKER_PROFILE?.size != it.contentsSpeakerList?.size }.size
        )
//        assertEquals(0, list.filter { it.company != it.companyName }.size)

        // relation test
        assertEquals(0, list.filter { it.relationList.CLASSIFICATION == null }.size)
        assertEquals(0, list.filter { it.relationList.MAIN_EXPOSURE_DAY == null }.size)
        assertEquals(0, list.filter { it.relationList.TECH_CLASSIFICATION == null }.size)

        assertEquals(90, list.filter { it.relationList.CLASSIFICATION.isEmpty() }.size)
        assertEquals(98, list.filter { it.relationList.MAIN_EXPOSURE_DAY.isEmpty() }.size)
        assertEquals(22, list.filter { it.relationList.TECH_CLASSIFICATION.isEmpty() }.size)

        assertEquals(22, list.filter { it.relationList.MAIN_EXPOSURE_DAY.size == 1 }.size)

        // session speaker test
        assertEquals(0, list.filter { it.contentsSpeakerList.isEmpty() }.size)

//        assertEquals(117, list.filter { it.contentsSpeakerList.any { it.channelLink == null } }.size)
        assertEquals(2, list.filter { it.contentsSpeakerList.any { it.company == null } }.size)

        sb = StringBuilder()
        list.filter { it.contentsSpeakerList.any { it.company == null } }.forEach {
            sb.append(it.title).append(" ${it.field}").append('\n')
        }
//        assertEquals("", sb.toString()) : 초청 강사 있는 듯.
//        assertEquals(0, list.filter { it.contentsSpeakerList.any { it.contentsIdx == null } }.size)
        assertEquals(0, list.filter { it.contentsSpeakerList.any { it.idx == null } }.size)
//        assertEquals(39, list.filter { it.contentsSpeakerList.any { it.loginEmail == null } }.size)
        assertEquals(0, list.filter { it.contentsSpeakerList.any { it.nameEn == null } }.size)
        assertEquals(0, list.filter { it.contentsSpeakerList.any { it.nameKo == null } }.size)
        assertEquals(7, list.filter { it.contentsSpeakerList.any { it.occupation == null } }.size)

        sb = StringBuilder()
        list.filter { it.contentsSpeakerList.any { it.occupation == null } }.forEach {
            sb.append(it.title).append(" ${it.field}").append('\n')
        }
//        assertEquals("", sb.toString())
    }
}