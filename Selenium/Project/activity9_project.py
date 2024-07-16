from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager
import time

# Setup the Firefox driver
driver = webdriver.Firefox(service=FirefoxService(GeckoDriverManager().install()))

# Initialize wait object
wait = WebDriverWait(driver, 10)

try:
    # Open browser
    driver.get("https://alchemy.hguy.co/lms/")

    # Login to application
    driver.find_element(By.XPATH, "//*[contains(text(),'My Account')]").click()
    title = driver.title
    print("Title of the front Page is :", title)
    
    driver.find_element(By.XPATH, "//*[contains(text(),'Login')]").click()
    driver.find_element(By.ID, "user_login").send_keys("root")
    driver.find_element(By.ID, "user_pass").send_keys("pa$$w0rd")
    driver.find_element(By.ID, "wp-submit").click()
    
    # Select the menu item that says “All Courses” and click it
    driver.find_element(By.XPATH, "//*[contains(text(),'All Courses')]").click()
    page_title = driver.title
    print("Title of the All Courses Page is :", page_title)
    
    # Select any course and open it
    driver.find_element(By.XPATH, "//article[@id='post-69']/div[2]/p[2]/a[1]").click()
    
    # Select Developing Strategy from course
    driver.find_element(By.XPATH, "//div[contains(text(),' Developing Strategy ')]").click()
    course_title = driver.title
    print("Title of the course is :", course_title)
    
    # Select Lesson from Developing Strategy course
    driver.find_element(By.XPATH, "//span[contains(text(),'This is the First Topic')]").click()
    lesson_title = driver.title
    print("Title of the Lesson is :", lesson_title)
    
finally:
    # Close browser
    driver.close()
