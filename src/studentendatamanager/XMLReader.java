/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentendatamanager;

import java.util.ArrayList;

public class XMLReader
{

    //Get list of elements with tag included from list of strings source
    public static ArrayList<String> getElementsPlus(String tag, ArrayList<String> source)
    {
        boolean noEndTag = true;
        for (String sourceLine : source)
        {
            if (sourceLine.contains("</" + tag + ">"))
            {
                noEndTag = false;
            }
        }
        ArrayList<String> elements = new ArrayList<>();
        String element = "";
        String currentTag = "";
        boolean tagStart = false;
        boolean startTagFound = false;
        for (int elemId = 0; elemId < source.size(); elemId++)
        {
            int tagStartId = 0;
            for (int charId = 0; charId < source.get(elemId).length(); charId++)
            {
                if (!startTagFound)
                {
                    if (!tagStart)
                    {
                        if (source.get(elemId).charAt(charId) == '<')
                        {
                            tagStart = true;
                            tagStartId = charId;
                            element += "<";
                        }
                    }
                    else //if tagStart
                    {
                        element += source.get(elemId).charAt(charId);
                        if (source.get(elemId).charAt(charId) == '>')
                        {
                            tagStart = false;
                            if (currentTag.equals(tag))
                            {
                                startTagFound = true;
                            }
                            else
                            {
                                element = "";
                            }
                            currentTag = "";
                        }
                        else if (source.get(elemId).charAt(charId) == ' ')
                        {
                            tagStart = false;
                            if (currentTag.equals(tag))
                            {
                                //System.out.println("currentTag = \"" + currentTag +"\"");
                                startTagFound = true;
                            }
                            else
                            {
                                element = "";
                            }
                            currentTag = "";
                        }
                        else
                        {
                            currentTag += source.get(elemId).charAt(charId);
                        }
                    }
                }
                else //if startTagFound
                {
                    element += source.get(elemId).charAt(charId);
                    if (noEndTag)
                    {
                        if (source.get(elemId).charAt(charId) == '>')
                        {
                            tagStart = false;
                            noEndTag = false;
                            //System.out.println("element = \"" + element + "\"");
                            elements.add(element);
                            element = "";
                            startTagFound = false;
                            currentTag = "";
                        }
                    }
                    else //if !noEndTag
                     if (!tagStart)
                        {
                            if (source.get(elemId).charAt(charId) == '<')
                            {
                                tagStart = true;
                            }
                        }
                        else //if tagStart
                         if (source.get(elemId).charAt(charId) == '>')
                            {
                                tagStart = false;
                                if (currentTag.equals("/" + tag))
                                {
                                    elements.add(element);
                                    element = "";
                                    startTagFound = false;
                                }
                                currentTag = "";
                            }
                            else //if char != '>'
                            {
                                currentTag += source.get(elemId).charAt(charId);
                            }
                }
            }
        }
        return elements;
    }

    //Get element with tag included from string source
    public static String getElementPlus(String tag, String source)
    {
        ArrayList<String> sourceList = new ArrayList();
        sourceList.add(source);
        return getElementPlus(tag, sourceList);
    }

    //Get element with tag included from list of strings source
    public static String getElementPlus(String tag, ArrayList<String> source)
    {
        ArrayList<String> elements = getElementsPlus(tag, source);
        if (!elements.isEmpty())
        {
            return elements.get(0);
        }
        else
        {
            return "";
        }
    }

    //Get list of elements with tag included from string source
    public static ArrayList<String> getElementsPlus(String tag, String source)
    {
        ArrayList<String> sourceList = new ArrayList();
        sourceList.add(source);
        return getElementsPlus(tag, sourceList);
    }

    //Get attribute from a single element
    public static String getAttribute(String attriName, String element)
    {
        String attribute = "";
        if (element.contains(attriName + "="))
        {
            int index1 = element.indexOf(attriName + "=") + attriName.length() + 2;
            int index2 = element.indexOf("\"", index1);
            attribute = element.substring(index1, index2);
        }
        return attribute;
    }

    public static String stripTags(String tag, String element)
    {
        if (element.contains("<" + tag + ">") && element.contains("</" + tag + ">"))
        {
            element = (element.replace("<" + tag + ">", "")).replace("</" + tag + ">", "");
        }
        else if (element.contains("<" + tag + " ") && element.contains("</" + tag + ">"))
        {
            element = removeStartTag(tag, element);
            element = element.replace("</" + tag + ">", "");
        }
        else if (element.contains("<" + tag + " ") && countChar('>', element) == 1) // &! element.contains("</" + tag + ">")
        {
            element = (element.replace("<" + tag + " ", "")).replace(">", "");
        }
        else
        {
            System.out.println("XMLReader: stripTags: unknown input type: tag = " + tag + ", element = " + element);
        }
        return element;
    }

    private static String removeStartTag(String tag, String element)
    {
        String currentTag = "";
        boolean tagStart = false;
        boolean startTagFound = false;
        boolean tagRemoved = false;
        for (int i = 0; i < element.length(); i++)
        {
            if (!tagRemoved)
            {
                if (!startTagFound)
                {
                    if (!tagStart && element.charAt(i) == '<')
                    {
                        tagStart = true;
                        currentTag += '<';
                    }
                    else if (tagStart)
                    {
                        currentTag += element.charAt(i);
                        if (currentTag.equals("<" + tag))
                        {
                            startTagFound = true;
                        }
                        else if (element.charAt(i) == ' ' || element.charAt(i) == '>')
                        {
                            currentTag = "";
                            tagStart = false;
                        }

                    }
                    else; //pick your nose
                }
                else //if startTagFound
                {
                    currentTag += element.charAt(i);
                    if (element.charAt(i) == '>')
                    {
                        element = element.replace(currentTag, "");
                        tagRemoved = true;
                    }
                }
            }
            else
            {
                break;
            }
        }
        return element;
    }

    public static int countChar(char c, String s)
    {
        int amount = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == c)
            {
                amount++;
            }
        }
        return amount;
    }
}
