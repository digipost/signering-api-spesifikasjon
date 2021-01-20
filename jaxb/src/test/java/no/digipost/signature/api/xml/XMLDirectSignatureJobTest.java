/**
 * Copyright (C) Posten Norge AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.digipost.signature.api.xml;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static co.unruly.matchers.Java8Matchers.where;
import static co.unruly.matchers.OptionalMatchers.contains;
import static co.unruly.matchers.OptionalMatchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.is;

class XMLDirectSignatureJobTest {

    @Test
    void adaptSingularDocumentManifestToMultipleDocumentApi() {
        XMLDirectDocument document = new XMLDirectDocument("title", "description", XMLHref.of("href"), "application/pdf");
        XMLDirectSignatureJobManifest manifest = new XMLDirectSignatureJobManifest().withDocument(document);
        assertThat(manifest, where(XMLManifest::getDocumentsToSign, Matchers.contains(document)));
        assertThat(manifest.getJobInformation(), where(JobInformation::getTitle, both(is("title")).and(is(manifest.getDocument().getTitle()))));
        assertThat(manifest.getJobInformation(), where(JobInformation::getNonSensitiveTitle, empty()));
        assertThat(manifest.getJobInformation(), where(JobInformation::getDescription, both(contains("description")).and(contains(manifest.getDocument().getDescription()))));
    }

}