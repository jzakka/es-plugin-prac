package kr.co.wikibook

import org.elasticsearch.action.ActionType

const val NAME: String = "cluster:manage/example/helloworld"
object HelloWorldAction : ActionType<HelloWorldResponse>(NAME, ::HelloWorldResponse)