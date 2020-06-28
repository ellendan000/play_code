package devops.inbounds

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        urlPath '/customers/1/projects/2'
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                [
                        pipelineName: 'lead-management-normal-master',
                        env         : [
                                [
                                        name    : 'ci',
                                        sequence: 847,
                                ],
                                [
                                        name    : 'ci',
                                        sequence: 846,
                                ]

                        ]
                ],
                [
                        pipelineName: 'lead-management-normal-release',
                        env         : [
                                [
                                        name    : 'uat',
                                        sequence: 843,
                                ]
                        ]
                ]
        ])
    }
}