class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{}

		"/"(controller:"test",action:"about")
		"500"(view:'/error')
	}
}
