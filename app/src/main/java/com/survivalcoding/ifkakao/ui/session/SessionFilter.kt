package com.survivalcoding.ifkakao.ui.session

import com.survivalcoding.ifkakao.domain.model.Data

data class SessionFilter(
    val dayFilter: Int,
    val fieldFilters: Map<String, Boolean>,
    val classFilters: Map<String, Boolean>,
    val techFilters: Map<String, Boolean>,
    val companyFilters: Map<String, Boolean>,
) {
    fun filter(sessions: List<Data>): List<Data> {
        return sessions.filter(::filterDay).filter(::filterField).filter(::filterClass)
            .filter(::filterTech)
            .filter(::filterCompany)
    }

    fun getFilterCount(): Int {
        return fieldFilters.count { it.value } + classFilters.count { it.value } + techFilters.count { it.value } + companyFilters.count { it.value }
    }

    private fun filterDay(data: Data): Boolean {
        return if (dayFilter == 3) {
            true
        } else {
            data.relationList.MAIN_EXPOSURE_DAY.any {
                it.contains(dayFilter.toString())
            }
        }
    }

    private fun filterField(data: Data): Boolean {
        return if (fieldFilters.all { !it.value }) {
            true
        } else {
            fieldFilters.filter { it.value }.any { it.key == data.field }
        }
    }

    private fun filterClass(data: Data): Boolean {
        return if (classFilters.all { !it.value }) {
            true
        } else {
            data.relationList.CLASSIFICATION.any { classification ->
                classFilters.filter { it.value }.any {
                    classification == it.key
                }
            }
        }
    }

    private fun filterTech(data: Data): Boolean {
        return if (techFilters.all { !it.value }) {
            true
        } else {
            data.relationList.TECH_CLASSIFICATION.any { tech ->
                techFilters.filter { it.value }.any {
                    tech == it.key
                }
            }
        }
    }


    private fun filterCompany(data: Data): Boolean {
        return if (companyFilters.all { !it.value }) {
            true
        } else {
            companyFilters.filter { it.value }.any { it.key == data.companyName }
        }
    }
}