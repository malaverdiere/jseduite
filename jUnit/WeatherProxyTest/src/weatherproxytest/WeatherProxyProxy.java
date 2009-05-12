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
import fr.unice.i3s.modalis.jseduite.orchestrations.schema.weatherproxy.*;
import javax.xml.ws.WebServiceException;

public class WeatherProxyProxy {

    public static void main(String[] args) throws Exception {
        try {
            WeatherProxyProxy p = new WeatherProxyProxy();
            p.getWeather("62201");
        } catch(WebServiceException e) {
            e.printStackTrace();
        }
    }

    public WeatherInformation getWeather(String cityCode) throws Exception {
        fr.unice.i3s.modalis.jseduite.orchestrations.wsdl.weatherproxy.WeatherProxyService service = new fr.unice.i3s.modalis.jseduite.orchestrations.wsdl.weatherproxy.WeatherProxyService();
        fr.unice.i3s.modalis.jseduite.orchestrations.wsdl.weatherproxy.WeatherProxyPortType port = service.getWeatherProxyPort();
        WeatherProxyIn in = new WeatherProxyIn();
        in.setCityCode(cityCode);
        WeatherProxyOut result = port.getWeather(in);
        return result.getReturn();
    }
}
