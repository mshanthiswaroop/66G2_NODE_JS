from lxml import etree

# Validate using DTD
try:
    doc = etree.parse("greencampus.xml")
    dtd = etree.DTD("greencampus.dtd")

    if dtd.validate(doc):
        print("DTD validation successful!")
    else:
        print(dtd.error_log)

except Exception as e:
    print("DTD Error:", e)


# Validate using XSD
try:
    doc = etree.parse("greencampus.xml")
    xsd_doc = etree.parse("greencampus.xsd")
    schema = etree.XMLSchema(xsd_doc)

    if schema.validate(doc):
        print("XSD validation successful!")
    else:
        print(schema.error_log)

except Exception as e:
    print("XSD Error:", e)