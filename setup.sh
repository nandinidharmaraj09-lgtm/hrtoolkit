#!/bin/bash

# HR Toolkit - Automated Setup Script
# This script will set up MySQL and initialize the HR Toolkit backend

set -e  # Exit on error

echo "=========================================="
echo "HR Toolkit - Automated Setup"
echo "=========================================="
echo ""

# Check if MySQL is installed
echo "📦 Checking if MySQL is installed..."
if ! command -v mysql &> /dev/null; then
    echo "❌ MySQL is not installed. Please install MySQL first:"
    echo "   brew install mysql"
    exit 1
fi

echo "✅ MySQL is installed"
echo ""

# Start MySQL service
echo "🚀 Starting MySQL service..."
brew services start mysql
sleep 2
echo "✅ MySQL service started"
echo ""

# Wait a moment for MySQL to fully start
sleep 3

# Create database and user
echo "🔧 Creating database 'hr_toolkit'..."
mysql -u root -p"Nandu!@" -e "CREATE DATABASE IF NOT EXISTS hr_toolkit;" 2>/dev/null || {
    echo "⚠️  Attempting to create database without password..."
    mysql -u root -e "CREATE DATABASE IF NOT EXISTS hr_toolkit;"
}
echo "✅ Database 'hr_toolkit' created/verified"
echo ""

# Verify database was created
echo "📋 Verifying database..."
mysql -u root -p"Nandu!@" -e "SHOW DATABASES;" 2>/dev/null | grep -q hr_toolkit && echo "✅ Database verified" || echo "⚠️  Database verification skipped"
echo ""

# Build Maven project
echo "🔨 Building Maven project..."
mvn clean install -DskipTests
echo "✅ Maven build completed"
echo ""

echo "=========================================="
echo "✅ Setup Complete!"
echo "=========================================="
echo ""
echo "Next steps:"
echo "1. Open IntelliJ IDEA"
echo "2. Open the hrtoolkit project"
echo "3. Right-click on HRToolkitApplication.java"
echo "4. Click 'Run HRToolkitApplication'"
echo ""
echo "Your backend will start on: http://localhost:8080"
echo ""
echo "Test the API:"
echo "  GET  http://localhost:8080/api/categories"
echo "  POST http://localhost:8080/api/categories (with JSON body)"
echo ""
