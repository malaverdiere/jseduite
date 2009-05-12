/**
 * This file is part of jSeduite::WeatherProxy
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::WeatherProxy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::WeatherProxy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::WeatherProxy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Sebastien Mosser [mosser@polytech.unice.fr]
 **/

package weatherproxytest;

import fr.unice.i3s.modalis.jseduite.orchestrations.schema.weatherproxy.ForecastWeather;
import fr.unice.i3s.modalis.jseduite.orchestrations.schema.weatherproxy.WeatherInformation;
import org.junit.Test;
import static org.junit.Assert.*;

/** Weather Proxy orchestration test
 * @author mosser
 */
public class WeatherProxyProxyTest {

    // City code for Nice weather station
    private static final String CITY_CODE = "62201";

    private WeatherInformation info;
    public WeatherProxyProxyTest() throws Exception {
        WeatherProxyProxy instance = new WeatherProxyProxy();
        this.info = instance.getWeather(CITY_CODE);
    }

    @Test
    public void testLiveWeatherStationId() throws Exception {
        System.out.println("LiveWeather: Station Id");
        String station = this.info.getLive().getStation();
        assertTrue("Bad Station Name!",station.startsWith("Nice"));
    }

    @Test()
    public void testLiveWeatherIcon() throws Exception {
        System.out.println("LiveWeather: Icon");
        assertFalse("Missing Icon !",this.info.getLive().getIcon().equals(""));
    }

    @Test
    public void testForecastLength() throws Exception {
        System.out.println("ForeCastWeather: Length");
        int length = this.info.getForecastStar().getItem().size();
        assertTrue("Bad list size !", length == 7);
    }

    @Test
    public void testForecastIcon() throws Exception {
        System.out.println("ForeCastWeather: Icon");
        for(ForecastWeather w: this.info.getForecastStar().getItem()){
            String icon = w.getIcon();
            assertNotNull("Missing Icon !",icon);
            assertFalse("Empty icon !", icon.equals(""));
        }
    }

}