/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2010 Vincent Massol.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package org.codehaus.cargo.maven2;

import java.io.File;

import junit.framework.TestCase;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.cargo.maven2.configuration.Container;
import org.codehaus.cargo.maven2.log.MavenLogger;
import org.codehaus.cargo.util.log.FileLogger;
import org.codehaus.cargo.util.log.Logger;

/**
 * Unit tests for {@link AbstractCargoMojo}.
 * 
 * @version $Id$
 */
public class CargoMojoTest extends TestCase
{
    /**
     * {@link AbstractCargoMojo} for testing.
     */
    public class TestableAbstractCargoMojo extends AbstractCargoMojo
    {
        /**
         * {@inheritDoc}
         * @see AbstractCargoMojo#doExecute()
         */
        @Override
        public void doExecute() throws MojoExecutionException
        {
            // Do nothing voluntarily for the test
        }
    }

    /**
     * {@link AbstractCargoMojo} for testing.
     */
    private TestableAbstractCargoMojo mojo;

    /**
     * {@inheritDoc}. Create the {@link AbstractCargoMojo} for testing.
     */
    @Override
    protected void setUp()
    {
        this.mojo = new TestableAbstractCargoMojo();
    }

    /**
     * Test logger creation when a log element is specified.
     * @throws Exception If anything goes wrong.
     */
    public void testCreateLoggerWhenLogElementSpecified() throws Exception
    {
        // Create temporary log file for the test
        File logFile = File.createTempFile("cargo-test-" + getClass().getName(), ".log");
        logFile.deleteOnExit();

        this.mojo.setContainerElement(new Container());
        this.mojo.getContainerElement().setLog(logFile);

        Logger logger = this.mojo.createLogger();
        assertEquals(FileLogger.class.getName(), logger.getClass().getName());
    }

    /**
     * Test logger creation when no log element is specified.
     */
    public void testCreateLoggerWhenLogElementNotSpecified()
    {
        Logger logger = this.mojo.createLogger();
        assertEquals(MavenLogger.class.getName(), logger.getClass().getName());
    }
}
