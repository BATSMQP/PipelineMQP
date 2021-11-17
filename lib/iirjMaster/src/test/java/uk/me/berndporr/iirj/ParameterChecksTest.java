package uk.me.berndporr.iirj;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;

import uk.me.berndporr.iirj.Butterworth;

import org.junit.Assert;
import org.junit.Test;

// Various impulse responses written out to files so that you can plot them
public class ParameterChecksTest {

	static String prefix="target/surefire-reports/parameter_checks/";

	void createDir() throws Exception {
		File dir = new File(prefix);
		dir.mkdirs();
	}		

	@Test
	public void correctFsTest() throws Exception {

		ButterworthJDSP butterworth1 = new ButterworthJDSP();
		butterworth1.lowPass(4, 250, 50);
		
		ButterworthJDSP butterworth2 = new ButterworthJDSP();
		butterworth2.bandPass(2, 250, 50, 5);

		ButterworthJDSP butterworth3 = new ButterworthJDSP();
		butterworth3.bandStop(2, 250, 50, 5);

		ButterworthJDSP butterworth4 = new ButterworthJDSP();
		butterworth4.highPass(4, 250, 50);
	}

	@Test
	public void wrongFcTestLowpass() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.lowPass(4, 250, 125);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Lowpass exception for fc = 0.5: "+e.getMessage());
		}
	}

	@Test
	public void wrongFcTestHighpass() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.highPass(4, 250, 125);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Highpass exception for fc = 0.5: "+e.getMessage());
		}
	}

	@Test
	public void wrongFcTestBandpass() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.bandPass(2, 250, 125, 5);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Bandpass exception for fc = 0.5: "+e.getMessage());
		}
	}

	@Test
	public void wrongFcTestBandstop() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.bandStop(2, 250, 125, 5);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Bandstop exception for fc = 0.5: "+e.getMessage());
		}
	}

	@Test
	public void negFcTestLowpass() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.lowPass(4, 250, -1);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Lowpass exception for fc < 0: "+e.getMessage());
		}
	}

	@Test
	public void negFcTestHighpass() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.highPass(4, 250, -1);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Highpass exception for fc < 0: "+e.getMessage());
		}
	}

	@Test
	public void negFcTestBandpass() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.bandPass(2, 250, -1, 5);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Bandpass exception for fc < 0: "+e.getMessage());
		}
	}

	@Test
	public void negFcTestBandstop() throws Exception {
		ButterworthJDSP butterworth = new ButterworthJDSP();
		try {
			butterworth.bandStop(2, 250, -1, 5);
			Assert.fail("Exception not generated for wrong fc.");
		} catch (Exception e) {
			System.out.println("Bandstop exception for fc < 0: "+e.getMessage());
		}
	}

	public void main(String args[]) {
		try {
			correctFsTest();
			wrongFcTestLowpass();
			wrongFcTestHighpass();
			wrongFcTestBandpass();
			wrongFcTestBandstop();
			negFcTestLowpass();
			negFcTestHighpass();
			negFcTestBandpass();
			negFcTestBandstop();
		} catch (Exception e) {
		}
	}
}
