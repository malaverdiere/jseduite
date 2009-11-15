This file described the content of the AbstractSource.bpel file, epxressing
constraints on "what your code should do" when writing a jSeduite Source
process.

############################
#### Partners Directory ####
############################

  - Import (New / External WSDL Documents) source WS artefact (XSD, WSDL)

######################
#### XSD Artefact ####
######################

  - AbstractSource.xsd [Mandatory]

First, add an 'import' directive to load your previously imported XSD schema.
<xsd:import schemaLocation="Partners/XXXService.xsd" namespace="http://..."/>
    
Edit the 'global type' 'Information', and add a new element inside the choice
structure:
<xsd:element name="xxx" type="ns:Xxx"/>

  - SourceName.xsd [Optional]

If a type 'SetOfXXX', where XXX is the name of your special information type
is not defined, define a 'SourceName.xsd' file which describe it. Create a XSD
file, import the service XSD file (where XXX is described), and define the 
SetOfXXX structure.

SourceName.xsd namespace should looks like:
http://modalis.i3s.unice.fr/jSeduite/sources/schemas/Information/SourceName

The set MUST be defined as the following:
<xsd:complexType name="SetOfXXX">
  <xsd:sequence>
    <xsd:element name="item" minOccurs="0" maxOccurs="unbounded" type="ns:XXX"/>
  </xsd:sequence>
</xsd:complexType>



#######################
#### WSDL Artefact ####
#######################

  - Copy / Paste 'AbstractSource.wsdl' into 'SourceName.wsdl'

  - Change the namespace in 'targetNamespace' and 'xmlns:tns' attributes 
http://modalis.i3s.unice.fr/jSeduite/sources/wsdl/SourceName

  - Replace Abstract by Source almost everywhere (except in types)


#######################
#### BPEL Artefact ####
#######################


  - Copy / Paste 'AbstractSource.bpel' into 'SourceName.bpel' .

  - Change the namespace in 'targetNamespace' and 'xmlns:tns' attributes 
http://modalis.i3s.unice.fr/jSeduite/sources/bpel/SourceName

  - Change the WSDL import (from AbstractSource.wsdl to SourceName.wsdl)

  - Replace 'Abstract' by 'Source' everywhere in the process code

At this step, the document validation process should not throw any error (only
warnings of implicit conversion from string to node)

  - If you've defined your own SourceName.xsd file, import it and declare the
    associated xmlns prefix (should be 'xmlns:ns2')

  - Import the XSD which defines the business element and declare its associated
    xmlns prefix (should be 'xmlns:ns3')

  - Drag'n drop the Partners/SourceService.wsdl file on the right of the
    process, rename the partner as 'source' for example.

  - Edit the BPEL Process behaviour:

## Activity a0 : Message reception

  - Replace 'SourceName' by your source name (profile manager related id)

## Scope policy_set_star: iteration over the parameter space

  - Change 'raw_star' variable type to 'SetOfXXX'.

## Activity a40 ...: parameter read

  - Use the flow to extract expected parameter from 'set'. Declare these params
    as variable (type xsd:string) inside the policy_set_star scope
<copy>
  <from>$set/values[name = 'PARAM']/value</from>
  <to variable="PARAM"/>
</copy>


## Activity a5: ws invocation

  - Perform the invocation. Put expected information set into 'raw_star'.

  - The a5_ns_hack helps you to avoid ns troubles. It should be an iteration
    over the service answer which feed the 'raw_star' variable. If not needed,
    just keep the 'a5_post' activity. you can access to '$cpt' which represents
    the current array index element while iterating over an aswer. Do not forget
    to change the while loop condition into somethong more conssitent ...

## Scope policy_raw_star: iteration over the raw information set

  - change 'raw' variable type to 'XXX'

## Activity 'policy_raw_star_loop'

  - put the right xmlns in the condition expression before 'item' in the
    condition. It should be 'ns2:' instead of 'ns0:' in most case.

## Activity 'policy_raw_star_loop_feed'

  - perform the same namespace change.

## Activity a7: toInfo transformation

  - Change 'raw' destination to the right choice element (cf XSD artefact step).


