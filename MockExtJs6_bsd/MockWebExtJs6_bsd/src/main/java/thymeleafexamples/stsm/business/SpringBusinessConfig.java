/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package thymeleafexamples.stsm.business;

import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
import org.springframework.test.context.ActiveProfiles;
at ActiveProfiles("Tomcat")
*/


@Configuration
@ComponentScan(basePackages={"thymeleafexamples.stsm.business","org.avp.quota.kpi.repository","org.avp.quota.kpi.service.interfaces","org.avp.quota.kpi.web.service","org.avp.quota.kpi.service","org.avp.quota.kpi.service","org.avp.bsd.service"})
@Import({ TomcatDataServiceModuleConfiguration.class})
public class SpringBusinessConfig {

    public SpringBusinessConfig() {
        super();
    }

    // Nothing else to be configured here: component scanning will do everything needed

}
