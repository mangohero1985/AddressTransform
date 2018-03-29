/*
 * microsoft-translator-java-api-examples
 * 
 * Copyright 2011 Jonathan Griggs <jonathan.griggs at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * TranslateExample
 * 
 * A short, sweet demo on how to translate a String using microsoft-translator-java-api
 * 
 * @author griggs.jonathan
 * @date Jun 1, 2011
 * @since 0.3 June 1, 2011
 */
public class TestMicrosoftTranslate 
{
    public static void main( String[] args ) throws Exception {
        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
        Translate.setClientId("621767cc-e953-4d2d-978f-db49642f11ab");
        Translate.setClientSecret("pltDoRajFZrYdv4CDceGjNkrNBLNFvuXAbUOteo2KlI");
        String JapaneseText = "Japan";
        // From English —Japanese 
        String translatedText = Translate.execute(JapaneseText,  Language.ENGLISH,Language.JAPANESE);
        System.out.println("English -> Japanese: " + translatedText);
        
//        // From English -> French - AUTO_DETECT the From Language
//        translatedText = Translate.execute("Hello world!",Language.FRENCH);
//        System.out.println("English AUTO_DETECT -> French: " + translatedText);
//        
//        // English AUTO_DETECT -> Arabic
//        translatedText = Translate.execute("Hello world, how are you doing?",Language.ARABIC);
//        System.out.println("English AUTO_DETECT -> Arabic: " + translatedText);
    }
}
